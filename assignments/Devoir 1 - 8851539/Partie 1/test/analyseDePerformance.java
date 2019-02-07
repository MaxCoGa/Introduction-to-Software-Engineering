package test;//Maxime Côté-Gagné(8851539)
/*
* Analyse de performances des PoinCP,PoinCP2,PoinCP3 et PointCP5
* @author Maxime Côté-Gagné
*/
public class analyseDePerformance  {
    /**
     * Find average time to get each Cartesian coordinate from getter method in PointCP
     * @param type char value of either C or P
     * @param testTimeSeconds time to run test in seconds
     * @return average operation time in nanoseconds
     */
    public static long getCPointCP(char type, long testTimeSeconds){
        long testTimeNano = testTimeSeconds * 1000 * 1000 * 1000;
        long count = 0;
        long start = System.nanoTime();
        long limit = start + testTimeNano;
        PointCP tmp = new PointCP(type, 10*Math.random(), 10*Math.random());
        while(System.nanoTime() < limit){
            tmp.getX();
            count++;
            tmp.getY();
            count++;
        }
        long result = testTimeNano/count;
        return result;
    }

    /**
     * Find average time to get each Cartesian coordinate from getter method in PointCP Design 2
     * @param type char value of either C or P
     * @param testTimeSeconds time to run test in seconds
     * @return average operation time in nanoseconds
     */
    public static long getCPointCP2(char type, long testTimeSeconds) {
        long testTimeNano = testTimeSeconds * 1000 * 1000 * 1000;
        long count = 0;
        long start = System.nanoTime();
        long limit = start + testTimeNano;
        PointCP2 tmp = new PointCP2(type, 10 * Math.random(), 10 * Math.random());
        while (System.nanoTime() < limit) {
            tmp.getX();
            count++;
            tmp.getY();
            count++;
        }
        long result = testTimeNano / count;
        return result;
    }
    
    /**
     * Find average time to get each Cartesian coordinate from getter method in PointCP Design 3
     * @param type char value of either C or P
     * @param testTimeSeconds time to run test in seconds
     * @return average operation time in nanoseconds
     */
    public static long getCPointCP3(char type, long testTimeSeconds) {
        long testTimeNano = testTimeSeconds * 1000 * 1000 * 1000;
        long count = 0;
        long start = System.nanoTime();
        long limit = start + testTimeNano;
        PointCP3 tmp = new PointCP3(type, 10 * Math.random(), 10 * Math.random());
        while (System.nanoTime() < limit) {
            tmp.getX();
            count++;
            tmp.getY();
            count++;
        }
        long result = testTimeNano / count;
        return result;
    }

    /**
     * Find average time to get each Cartesian coordinate from getter method in PointCP Design 5
     * @param type char value of either C or P
     * @param testTimeSeconds time to run test in seconds
     * @return average operation time in nanoseconds
     */
    public static long getCPointCP5(char type, long testTimeSeconds) {
        long testTimeNano = testTimeSeconds * 1000 * 1000 * 1000;
        long count = 0;
        long start = System.nanoTime();
        long limit = start + testTimeNano;
        PointCP5 tmp = new PointCPCartesian(type, 10 * Math.random(), 10 * Math.random());
        while (System.nanoTime() < limit) {
            tmp.getX();
            count++;
            tmp.getY();
            count++;
        }
        long result = testTimeNano / count;
        return result;
    }

    /**
     * Find average time to get each Polar  coordinate from getter method in PointCP
     * @param type char value of either C or P
     * @param testTimeSeconds time to run test in seconds
     * @return average operation time in nanoseconds
     */
    public static long getPPointCP(char type, long testTimeSeconds) {
        long testTimeNano = testTimeSeconds * 1000 * 1000 * 1000;
        long count = 0;
        long start = System.nanoTime();
        long limit = start + testTimeNano;
        PointCP tmp = new PointCP(type, 10 * Math.random(), 10 * Math.random());
        while (System.nanoTime() < limit) {
            tmp.getRho();
            count++;
            tmp.getTheta();
            count++;
        }
        long result = testTimeNano / count;
        return result;
    }

    /**
     * Find average time to get each Polar  coordinate from getter method in PointCP Design 2
     * @param type char value of either C or P
     * @param testTimeSeconds time to run test in seconds
     * @return average operation time in nanoseconds
     */
    public static long getPPointCP2(char type, long testTimeSeconds) {
        long testTimeNano = testTimeSeconds * 1000 * 1000 * 1000;
        long count = 0;
        long start = System.nanoTime();
        long limit = start + testTimeNano;
        PointCP2 tmp = new PointCP2(type, 10 * Math.random(), 10 * Math.random());
        while (System.nanoTime() < limit) {
            tmp.getRho();
            count++;
            tmp.getTheta();
            count++;
        }
        long result = testTimeNano / count;
        return result;
    }
    /**
     * Find average time to get each Polar  coordinate from getter method in PointCP Design 3
     * @param type char value of either C or P
     * @param testTimeSeconds time to run test in seconds
     * @return average operation time in nanoseconds
     */
    public static long getPPointCP3(char type, long testTimeSeconds) {
        long testTimeNano = testTimeSeconds * 1000 * 1000 * 1000;
        long count = 0;
        long start = System.nanoTime();
        long limit = start + testTimeNano;
        PointCP3 tmp = new PointCP3(type, 10 * Math.random(), 10 * Math.random());
        while (System.nanoTime() < limit) {
            tmp.getRho();
            count++;
            tmp.getTheta();
            count++;
        }
        long result = testTimeNano / count;
        return result;
    }

    /**
     * Find average time to get each Polar  coordinate from getter method in PointCP Design 5
     * @param type char value of either C or P
     * @param testTimeSeconds time to run test in seconds
     * @return average operation time in nanoseconds
     */
    public static long getPPointCP5(char type, long testTimeSeconds) {
        long testTimeNano = testTimeSeconds * 1000 * 1000 * 1000;
        long count = 0;
        long start = System.nanoTime();
        long limit = start + testTimeNano;
        PointCP5 tmp = new PointCPPolar(type, 10 * Math.random(), 10 * Math.random());
        while (System.nanoTime() < limit) {
            tmp.getRho();
            count++;
            tmp.getTheta();
            count++;
        }
        long result = testTimeNano / count;
        return result;
    }

    /**
     * Main loop to run tests and print results out to user
     * @param args
     */
    public static void main(String[] args){
        int testTime = 1;

        System.out.println("Average time for:\n");
        
        System.out.println("Cartesian from Cartesian PointCP: "+getCPointCP('C', testTime)+"ns");
        System.out.println("Cartesian from Cartesian PointCPD2: "+getCPointCP2('C', testTime)+"ns");
        System.out.println("Cartesian from Cartesian PointCPD3: "+getCPointCP3('C', testTime)+"ns");
        System.out.println("Cartesian from Cartesian PointCPD5: "+getCPointCP5('C', testTime)+"ns");
        
        System.out.println("\nPolar from Cartesian PointCP: "+getPPointCP('C', testTime)+"ns");
        System.out.println("Polar from Cartesian PointCPD2: "+getPPointCP2('C', testTime)+"ns");
        System.out.println("Polar from Cartesian PointCPD3: "+getPPointCP3('C', testTime)+"ns");
        System.out.println("Polar from Cartesian PointCPD5: "+getPPointCP5('C', testTime)+"ns");

        System.out.println("\nCartesian from Polar PointCP: "+getCPointCP('P', testTime)+"ns");
        System.out.println("Cartesian from Polar PointCPD2: "+getCPointCP2('P', testTime)+"ns");
        System.out.println("Cartesian from Polar PointCPD3: "+getCPointCP3('P', testTime)+"ns");
        System.out.println("Cartesian from Polar PointCPD5: "+getCPointCP5('P', testTime)+"ns");
        
        System.out.println("\nPolar from Polar PointCP: "+getPPointCP('P', testTime)+"ns");
        System.out.println("Polar from Polar PointCPD2: "+getPPointCP2('P', testTime)+"ns");
        System.out.println("Polar from Polar PointCPD3: "+getPPointCP3('P', testTime)+"ns");
        System.out.println("Polar from Polar PointCPD5: "+getPPointCP5('P', testTime)+"ns");
    }
}