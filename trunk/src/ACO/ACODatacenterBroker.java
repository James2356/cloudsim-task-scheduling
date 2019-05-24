package ACO;

import org.cloudbus.cloudsim.*;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.core.CloudSimTags;
import org.cloudbus.cloudsim.core.SimEvent;
import org.cloudbus.cloudsim.lists.VmList;
import utils.Constants;

import java.util.List;

public class ACODatacenterBroker extends DatacenterBroker {
    private double[] mapping;

    public ACODatacenterBroker(String name) throws Exception {
        super(name);
    }


    /**
     * bind Cloudlet to Vm based on ACO
     * @param antcount 蚂蚁个数
     * @param maxgen 最大迭代次数
     * **/
    //public void init(int antNum, List<? extends Cloudlet> cloudletList, List<? extends Vm> vmList)
    //public void run(int maxgen)
    public void RunACO(int antcount, int maxgen){
        long time = System.currentTimeMillis();
        ACO aco;
        aco = new ACO();
        aco.init(antcount, cloudletList, vmList);
        aco.run(maxgen);
        aco.ReportResult();
        double[] bestposition = new double[Constants.NO_OF_TASKS];
        for (int i = 0; i < cloudletList.size(); i++) {
            cloudletList.get(aco.bestTour[i].task).setVmId(aco.bestTour[i].vm);
            bestposition[i] = aco.bestTour[i].vm;
        }
        ACOFitnessFunction ff = new ACOFitnessFunction();
        System.out.println("best totalcost:"+ff.calcTotalTime(bestposition));
    }

}
