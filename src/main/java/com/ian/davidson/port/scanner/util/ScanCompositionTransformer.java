package com.ian.davidson.port.scanner.util;

import com.ian.davidson.port.scanner.model.business.ScanComposition;
import com.ian.davidson.port.scanner.model.request.ScanRequest;
import org.springframework.stereotype.Component;

@Component
public class ScanCompositionTransformer {
    public ScanComposition toScanCompositionTransformer(final ScanRequest scanRequest){
       /*
   private List<Integer> ports = new ArrayList<>();
    private Integer portRangeStart;
    private Integer portRangeEnd;

    private List<Integer> ips = new ArrayList<>();
    private Integer ipRangeStart;
    private Integer ipRangeEnd;
        */

        if(scanRequest.getPorts() == null || scanRequest.getPorts().isEmpty()){
            //use range
        }else{

        }


    }
}
