package gaapplication1;

public class GAApplication1 {

    public static void main(String[] args) {
        String target = "To be or not to be.";
        double mutationRate = 0.1;
        int popmax = 200;
        boolean optimize = true;

        //step 1 : initialize - create population of N elements
        Population p = new Population(target, mutationRate, popmax);
        System.out.println("test 1");
        p.createPopulation();
        System.out.println("test 2");

        //step 2 : selection - evaluate the fitness of each element and build the mating pool
        //*********** loop *********
        do {

            p.calcFitness();
            System.out.println("test 3");
            p.naturalSelection();
            System.out.println("test 4");

            //step 3 : reproduction
            // -pick two parents with probability according to relative fitness
            // -crossover : create a child by combining the chromosome genes of the two parents
            // -mutation : mutate the child's gene based on a given probability
            // -add the new child to a population
            p.generate();
            System.out.println("test 5");

            //finalizing
            p.evaluate();

            String ans = "";
            char[] answer = p.getBest();
            //System.out.println("Best Phrase is : ");
            for (int i = 0; i < answer.length; i++) {
                //System.out.print(answer[i] + "");
                ans += answer[i];
            }
            System.out.println();
            System.out.println("answer :" + ans);
            System.out.println("target :" + target);
            System.out.println();
            if (ans.equals(target)) {
                optimize = false;
            }
            //String answer = p.getBest().toString();
            //System.out.println("Best Phrase : " + answer);

        } while (optimize);
        
        String statstext = "Total Generations: " + p.getGeneration() + "\n";
       // statstext += "average fitness: " + p.getAverageFitness() + "\n";
        statstext += "total population: " + popmax + "\n";
        statstext += "mutation rate: " + mutationRate + "%";

        System.out.println(statstext);
    }

}
