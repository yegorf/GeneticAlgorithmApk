package com.example.geneticalgorithm.logic;

import com.example.geneticalgorithm.tools.Chromosome;
import com.example.geneticalgorithm.tools.FitnessFunction;
import com.example.geneticalgorithm.utils.NumberUtil;

import java.util.ArrayList;
import java.util.Random;

public class Genetic {
    private static final int INTERVALS_COUNT = 500;
    Random random = new Random();

    public void printGenome(ArrayList<Chromosome> genome) {
        for(Chromosome c : genome) {
            System.out.println(c.getBinary() + " " + c.getDecimal());
        }
        System.out.println();
    }

    public Chromosome evolution() {
        int iterationsCount = 10;
        int chromosomesCount = 10; //Количество Хромосом
        double minInterval = 0; //Начало отрезка
        double maxInterval = 20; //Конец отрезка
        double operatorChance = 0.5;
        double mutationChance = 0.5;

        final int chromosomesSize = NumberUtil.getChromosomeSize((INTERVALS_COUNT * (int)(maxInterval - minInterval)));
        int count = 0; //количество итераций

        ArrayList<Chromosome> genome = createGenome(chromosomesSize, chromosomesCount);
        for(Chromosome c : genome) {
            c.setValue(minInterval, maxInterval);
        }

        System.out.println("Исходная популяция");
        printGenome(genome);
        System.out.println();

        while (count < iterationsCount) {
            //Репродукция генома
            System.out.println("Итерация " + (count+1));
            reproduction(genome, minInterval, maxInterval, chromosomesCount);
            printGenome(genome);

            for(Chromosome chromosome : genome) {
                //Красенговер ТУТ ПРОБЛЕМА!
                if(random.nextDouble() <= operatorChance) {
                    System.out.println("OPERATOR");
                    operator(genome, chromosome, minInterval, maxInterval);
                }

                //Мутация ПОРЯДОК!!!
                String bin = chromosome.getBinary();
                if(random.nextDouble() <= mutationChance) {
                    System.out.println("MUTATION");
                    chromosome.setBinary((mutation(bin)));
                    //chromosome.setValue(minInterval, maxInterval);
                    chromosome.setDecimal(NumberUtil.getChromosomeValue(chromosome.getBinary(), minInterval, maxInterval));
                }

                Double value = NumberUtil.getChromosomeValue(bin, minInterval, maxInterval);
                double functionValue = FitnessFunction.value(value);

                chromosome.setDecimal(value);
                chromosome.setFunctionValue(functionValue);
            }

            genome.addAll(createGenome(chromosomesSize, chromosomesCount - genome.size()));

            for(Chromosome c : genome) { //ДИШМАН
                c.setValue(minInterval, maxInterval);
                Double fv = FitnessFunction.value(c.getDecimal());
                c.setFunctionValue(fv);
            }

            count++;
        }

        System.out.println("Финальный геном");
        printGenome(genome);

        Chromosome best = null;
        double bestFunc = -100000;


        for(Chromosome c : genome) {
            if(c.getFunctionValue() > bestFunc) { //NULLPOINER
                bestFunc = c.getFunctionValue();
                best = c;
            }
        }

        System.out.println("Лучшая хромосома: " + best.getBinary());
        System.out.println("Ее числовое значение: " + best.getDecimal());
        System.out.println("Значение фитнес-функции: " + best.getFunctionValue());

        return best;
    }

    //Мутация
    private String mutation(String chromosome) {
        StringBuilder newChromosome = new StringBuilder(chromosome);
        int i = random.nextInt(chromosome.length());
        int c = newChromosome.charAt(i);

        if(c == '0') {
            newChromosome.setCharAt(i, '1');
        } else {
            newChromosome.setCharAt(i, '0');
        }
        return  newChromosome.toString();
    }

    //Оператор Красенговера
    private void operator(ArrayList<Chromosome> genome, Chromosome father, double minInterval, double maxInterval) {
        Chromosome mother = getParents(genome, father);
        if(mother != null) {
            int size = father.getBinary().length();
            System.out.println("Mother " + mother.getBinary());
            System.out.println("Father " + father.getBinary());
            String child = getChild(father.getBinary(), mother.getBinary(), createChromosome(size).getBinary());
            System.out.println("Child " + child);
            father.reborn(child);
            father.setDecimal(NumberUtil.getChromosomeValue(child, minInterval, maxInterval));
        }
    }

    //Скрещивание
    private String getChild(String father, String mother, String model) {
        char[] chars = model.toCharArray();
        StringBuilder child = new StringBuilder();
        for(int i=0; i<chars.length; i++) {
            if(chars[i] == '0') {
                child.append(father.charAt(i));
            } else {
                child.append(mother.charAt(i));
            }
        }
        return child.toString();
    }

    //Подбор пары
    private Chromosome getParents(ArrayList<Chromosome> genome, Chromosome father) {
        boolean contains = false;
        for(Chromosome c : genome) {
            contains = !c.equals(father);
        }

        if(contains) {
            Chromosome mother;
            do {
                mother = genome.get(random.nextInt(genome.size()));
            } while (mother.equals(father));

            return mother;
        } else {
            return null;
        }
    }

    //Репродукция - отбор родителей
    private void reproduction(ArrayList<Chromosome> genome, double minInterval, double maxInterval,
                              int chromosomesCount) {
        double fValuesSum = 0;
        for(Chromosome c : genome) {
            double functionValue = FitnessFunction.value(NumberUtil.getChromosomeValue(c.getBinary(), minInterval, maxInterval));
            c.setFunctionValue(functionValue);
            fValuesSum += functionValue;
        }

        ArrayList<Chromosome> newGenome = new ArrayList<>();

        for(Chromosome c : genome) {
            long count = Math.round((c.getFunctionValue() / fValuesSum) * chromosomesCount);
            for(int i=0; i<count; i++) {
                newGenome.add(c.copy()); // +
            }
        }

        genome.clear();
        genome.addAll(newGenome); //?
    }

    private ArrayList<Chromosome> createGenome(int size, int count) {
        ArrayList<Chromosome> genome = new ArrayList<>();
        for(int i=0; i<count; i++) {
            genome.add(createChromosome(size));
        }
        return genome;
    }

    private Chromosome createChromosome(int size) {
        StringBuilder value = new StringBuilder();
        for(int i=0; i<size; i++) {
            if(random.nextDouble() <= 0.5) {
                value.append('1');
            } else {
                value.append('0');
            }
        }
        return new Chromosome(value.toString());
    }
}
