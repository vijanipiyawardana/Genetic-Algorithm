package gaapplication1;

import static java.lang.Math.min;
import java.util.ArrayList;
import java.util.Random;

public class Population {

    private int popmax;
    private Chromosome population[];
    private String target;
    private double mutationRate;

    private ArrayList<Chromosome> matingPool = new ArrayList<Chromosome>();
    private int generation = 0;

    public int getGeneration() {
        return generation;
    }

    public Population(String target, double mutationRate, int popmax) {
        this.target = target;
        this.mutationRate = mutationRate;
        this.popmax = popmax;
        population = new Chromosome[popmax];
    }

    public void createPopulation() {
        for (int i = 0; i < popmax; i++) {
            System.out.print((i + 1) + "\t");
            Chromosome chromosome = new Chromosome(target.length());
            population[i] = chromosome; 
        }
    }

    void calcFitness() {
        System.out.println("Test 6");
        for (int i = 0; i < this.population.length; i++) {
            this.population[i].calculateFitness(target);
            System.out.print("fitness : "+ population[i].getFitness()+ " | ");
        }
        System.out.println();
    }

    void naturalSelection() {
        this.matingPool.clear();
        double maxFitness = 0;
        for (int i = 0; i < this.population.length; i++) {
            if (this.population[i].getFitness() > maxFitness) {
                maxFitness = this.population[i].getFitness();
            }
        }
        System.out.println("maxFitness :..." + maxFitness);
        for (int i = 0; i < this.population.length; i++) {
            //normalize the fitness values
            //double fitness = map(this.population[i].getFitness(), 0, maxFitness, 0,1);  
            double fitness = this.population[i].getFitness();
            int n = (int) fitness;
            //for (int j = 0; j <= n; j++) {
            for (int j = 0; j < n; j++) {
                this.matingPool.add(this.population[i]);
            }
        }
        System.out.println("population size : " + this.population.length);
        System.out.println("mating pool size : " + this.matingPool.size());

    }

    void generate() {
        System.out.println("Test 7");
        Random r = new Random();
        for (int i = 0; i < this.population.length; i++) {
            int a = r.nextInt((this.matingPool.size()-1) - 0) + 0;
            int b = r.nextInt((this.matingPool.size()-1) - 0) + 0;
            Chromosome parentA = this.matingPool.get(a);
            Chromosome parentB = this.matingPool.get(b);
            Chromosome child = parentA.crossover(parentB);
            child.mutate(this.mutationRate);
            this.population[i] = child;
        }
        this.generation++;
    }

    void evaluate() {

    }

    char[] getBest() {
        double worldrecord = 0.0;
        int index = 0;
        for (int i = 0; i < this.population.length; i++) {
            if (population[i].getFitness() > worldrecord) {
                index = i;
                worldrecord = population[i].getFitness();
            }
        }
        // if (worldrecord == perfectScore ) finished = true;
        return population[index].getPhrase();
    }

    double getAverageFitness() {
        double total = 0;
        for (int i = 0; i < population.length; i++) {
            total += population[i].getFitness();
            //System.out.print(population[i].getFitness() + " -- ");
        }
       // System.out.println("total "+ total+ " and length : " + population.length);
        return (total / (double)(population.length));
    }

    String allPhrases() {
        String everything = "";
        int displayLimit = min(population.length, 50);
        for (int i = 0; i < displayLimit; i++) {
            everything += population[i].getPhrase() + "\n";
        }
        return everything;
    }
}
