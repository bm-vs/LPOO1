package statistics;

public interface ProbabilityDistribution {
	public double getMean();
	public double getStddev();
	public double probabilityDensityFunction(double x);
	public double calcRangeProbability(int min, int max);
}
