package  dependencias_hh.jhelper.util;

import java.io.FileNotFoundException;
import java.io.IOException;

//import org.uma.jmetal.util.front.Front;

import dependencias_class.ArrayFront;
import dependencias_hh.jhelper.util.metrics.*;
import dependencias_interfaces.Front;
import dependencias_interfaces.Problem;

/**
 * The type Indicator factory.
 */
public class IndicatorFactoryRILA {

  /**
   * The constant Hypervolume.
   */
  public static final String Hypervolume = "Hypervolume";
  /**
   * The constant RniCalculator.
   */
  public static final String RNI = "RNI";
  /**
   * The constant IGD.
   */
  public static final String IGD = "IGD";
  /**
   * The constant IGDPlus.
   */
  public static final String IGDPlus = "IGDPlus";
  /**
   * The constant R.
   */
  public static final String R = "R";
  /**
   * The constant GD.
   */
  public static final String GD = "GD";
  /**
   * The constant Spread.
   */
  public static final String Spread = "Spread";
  /**
   * The constant Epsilon.
   */
  public static final String Epsilon = "Epsilon";
  
  /**
   * The constant Uniform distribution.
   */
  public static final String UD = "UD";
  
  /**
   * The constant Algorithm Effort.
   */
  public static final String AlgorithmEffort = "AE";
  
  /**
   * The constant Hypervolume.
   */
  public static final String FastHypervolume = "FastHypervolume";
  
  /**
   * The constant Hypervolume.
   */
  public static final String Spacing = "Spacing";
  
  /**
   * The constant Hypervolume.
   */
  public static final String NR = "NR";
  
  public static final String HR = "HR";

  /**
   * Build calculator calculator.
   *
   * @param qualityIndicatorName the quality indicator name
   * @param problem the problem
   * @param populationSize the population size
   * @return the calculator
   * @throws java.io.FileNotFoundException file not found.
   * @throws java.io.IOException file not found.
   */
  public static Calculator buildCalculator(String qualityIndicatorName, Problem problem,
      int populationSize) throws FileNotFoundException, IOException {
	  
	  System.out.println("BUILD CALCULATOR? %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"
	  		+ "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"
	  		+ "*******************************************************************************************************************"
	  		+ "BUILD BUILD &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&"
	  		+ "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&"
	  		+ ";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;"
	  		+ ";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;"
	  		+ ";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;"
	  		+ ";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;"
	  		+ "999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
    String pf =
        "pareto_fronts/" + problem.getName() + "." + problem.getNumberOfObjectives() + "D.pf";
    if(problem.getName().equals("UF")){
        pf =
        "pareto_fronts/" + problem.getClass().getSimpleName() + "." + problem.getNumberOfObjectives() + "D.pf";
    }
    switch (qualityIndicatorName) {
      case IndicatorFactoryRILA.Hypervolume:
        HypervolumeCalculator hyp=new HypervolumeCalculator(problem.getNumberOfObjectives());
        if(problem.getName().startsWith("WFG") || problem.getName().startsWith("DTLZ") || problem.getName().startsWith("UF") || problem.getName().startsWith("ZDT")){
            hyp.setParetoTrueFront((Front) new ArrayFront(pf));
        }
        else{
            //Real problems will not use that, keeping for safe
            hyp.addParetoFront((Front) new ArrayFront(pf));
        }
        return hyp;
      case IndicatorFactoryRILA.FastHypervolume:
        return new QuickHypervolumeCalculator(problem.getNumberOfObjectives());
      case IndicatorFactoryRILA.Epsilon:
        return new EpsilonCalculator(problem.getNumberOfObjectives(), pf);
      case IndicatorFactoryRILA.RNI:
        return new RniCalculator(problem.getNumberOfObjectives(), populationSize, pf);
      case IndicatorFactoryRILA.IGD:
        return new IgdCalculator(problem.getNumberOfObjectives(), pf);
      case IndicatorFactoryRILA.IGDPlus:
        return new IgdPlusCalculator(problem.getNumberOfObjectives(), pf);
      case IndicatorFactoryRILA.R:
        return new RCalculator(problem.getNumberOfObjectives(), populationSize, pf);
      case IndicatorFactoryRILA.Spread:
        return new SpreadCalculator(problem.getNumberOfObjectives(), pf);
      case IndicatorFactoryRILA.AlgorithmEffort:
        AlgorithmEffortCalculator ae=new AlgorithmEffortCalculator(populationSize, populationSize, populationSize);
        ae.setParetoTrueFront((Front) new ArrayFront(pf));//necessary for compatibility
        return ae;
      case IndicatorFactoryRILA.UD:
        return new UDMetricHandler(problem.getNumberOfObjectives(), pf);
      //case IndicatorFactory.Spacing:
        //return new SpacingCalculator(problem.getNumberOfObjectives(), pf);
      case IndicatorFactoryRILA.NR:
        return new NRCalculator(problem.getNumberOfObjectives(), populationSize, pf);
      case IndicatorFactoryRILA.HR:
        return new HypervolumeRatioCalculator(problem.getNumberOfObjectives());
      default:
        return null;
    }
  }
}