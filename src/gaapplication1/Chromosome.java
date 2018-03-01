package gaapplication1;

import java.util.Random;

public class Chromosome {

    private char genes[];
    private double fitness = 0;
    private int num;
    
    public Chromosome(int length) {
        this.num = length;
        genes = new char[num];
        this.createChromosome();
        this.printChromosome();
    }

    public void createChromosome(){
        for(int i=0; i<num; i++){
            this.genes[i] = newChar();   
        }
    }
    private void printChromosome(){
        for(int i=0; i<num; i++){
            System.out.print(this.genes[i] + "");   
        }
        System.out.println();
    }

    private char newChar() {
        Random r = new Random();
        int low = 63;
        int high = 122;
        int s = r.nextInt(high - low) + low;
        if(s == 63) 
            s = 32;
        if(s == 64) 
            s = 46;
        char letter = (char)s;
        return letter;
    }
    
    public void calculateFitness(String t){
        int score = 0;
        //System.out.print("genes length :"+ this.genes.length + " ");
        for(int i=0; i<this.genes.length; i++){
            if(this.genes[i] == t.charAt(i)){
               // System.out.println("gene : "+ this.genes[i] + " t: " + t.charAt(i));
               score++ ;
            }
        }
        double fit = score;
        this.fitness = fit;
        /*double fit = (double)score / t.length();
        System.out.println("score : "+score);
        System.out.println("fit : "+fit);
        this.fitness = fit;*/
        //return fit;
    }

    public double getFitness() {
        return fitness;
    }

    Chromosome crossover(Chromosome parentB) {
        Random r = new Random();
        Chromosome child = new Chromosome(num);
        int midPoint = r.nextInt(num - 0) + 0;
        // half from one, half from other
        for(int i=0; i< num; i++){
            if(i>midPoint){
                child.genes[i] = this.genes[i];
            }else{
                child.genes[i] = parentB.genes[i];
            }
        }
        return child;
    }

    void mutate(double mutationRate) {
        Random r = new Random();
        for(int i=0; i< num; i++){
            if((r.nextDouble()*(num -0) + 0) < mutationRate){
               this.genes[i] = newChar();
            }
        }
    }

    char[] getPhrase() {
       return genes;
    }
    
}
