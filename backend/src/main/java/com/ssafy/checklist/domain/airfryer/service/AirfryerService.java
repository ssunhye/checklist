package com.ssafy.checklist.domain.airfryer.service;

import com.ssafy.checklist.domain.airfryer.controller.response.AirfryerGetRes;
import com.ssafy.checklist.domain.airfryer.entity.Airfryer;
import com.ssafy.checklist.domain.airfryer.entity.AirfryerPerformance;
import com.ssafy.checklist.domain.airfryer.repository.AirfryerPerformanceRepository;
import com.ssafy.checklist.domain.airfryer.repository.AirfryerRepository;
import com.ssafy.checklist.domain.common.entity.LowPriceInfo;
import com.ssafy.checklist.domain.common.repository.LowPriceInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AirfryerService {
    @Autowired
    AirfryerRepository airfryerRepository;

    @Autowired
    AirfryerPerformanceRepository airfryerPerformanceRepository;

    @Autowired
    LowPriceInfoRepository lowPriceInfoRepository;

    public List<AirfryerGetRes> findAirfryerListByFilter(Pageable pageable, Map<String, Object> filters) {

        Page<Airfryer> airfryerList = null;
        List<AirfryerGetRes> airfryerGetResList = new ArrayList<>();
        Specification<Airfryer> filter = getMultiFilter(filters);

        airfryerList = airfryerRepository.findAll(filter, pageable);

        for(Airfryer airfryer: airfryerList) {
            Optional<AirfryerPerformance> airfryerPerformance = airfryerPerformanceRepository.findById(airfryer.getPcode());
            if(airfryerPerformance.isPresent()) {
                airfryerGetResList.add(AirfryerGetRes.of(airfryer, airfryerPerformance.get()));
            }
        }

        return airfryerGetResList;
    }

    private Specification<Airfryer> getMultiFilter(Map<String, Object> filters) {
        return new Specification<Airfryer>() {
            @Override
            public Predicate toPredicate(Root<Airfryer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate p = cb.conjunction();
                List<Predicate> pList = new ArrayList<Predicate>();

                // ????????? ?????????
                if(filters.get("?????????") != null){
                    List<String> values = (ArrayList<String>) filters.get("?????????");

                    // ????????? ????????? ????????? ????????? ???????????? ????????? ?????? ????????? ?????? ?????? ?????? ????????? ??????
                    if(!values.equals("??????")) {
                        String pricezero = values.get(0).replace("??????", "");
                        String[] prices = pricezero.split("~");

                        // ????????? ????????? and??? ???????????? ?????? ??????????????? ?????? ?????????
                        if (prices[0].equals("")) { // ~5??????
                            long maxPrice = Long.parseLong(prices[1]+"0000");
                            p = cb.and(p, cb.lessThanOrEqualTo(root.get("price"), maxPrice));
                        } else if(prices.length == 2) { // 20??????~
                            long maxPrice = Long.parseLong(prices[1]+"0000");
                            long minPrice = Long.parseLong(prices[0]+"0000");
                            p = cb.and(p, cb.between(root.get("price"), minPrice, maxPrice));
                        } else { // 5~10??????
                            long minPrice = Long.parseLong(prices[0]+"0000");
                            p = cb.and(p, cb.greaterThanOrEqualTo(root.get("price"), minPrice));
                        }

                        // ?????? ???????????? ???????????? or??? ???????????? ???????????? ?????? ?????? ???????????? ?????? ?????????
                        for(int i=1, n=values.size(); i<n; i++) {
                            String pricezero2 = values.get(0).replace("??????", "");
                            String[] prices2 = pricezero2.split("~");

                            if (prices2[0].equals("")) {
                                long maxPrice2 = Long.parseLong(prices2[1] + "0000");
                                p = cb.or(p, cb.lessThanOrEqualTo(root.get("price"), maxPrice2));
                            } else if(prices2.length == 2) { // 20??????~
                                long maxPrice2 = Long.parseLong(prices2[1] + "0000");
                                long minPrice2 = Long.parseLong(prices2[0] + "0000");
                                p = cb.or(p, cb.between(root.get("price"), minPrice2, maxPrice2));
                            } else {
                                long minPrice2 = Long.parseLong(prices2[0] + "0000");
                                p = cb.or(p, cb.greaterThanOrEqualTo(root.get("price"), minPrice2));
                            }
                        }
                    }
                }

                // ?????? ?????????
                if(filters.get("??????") != null) {
                    List<String> values = (ArrayList<String>) filters.get("??????");
                    if(!values.contains("??????")) {
                        String type = values.get(0);
                        p = cb.and(p, cb.equal(root.get("type"),  type));

                        for(int i = 1; i < values.size(); i++) {
                            String type2 = values.get(i);
                            p = cb.or(p, cb.equal(root.get("type"),  type2));
                        }
                    }
                }

                // ?????? ?????????
                if(filters.get("??????") != null){
                    List<String> values = (ArrayList<String>) filters.get("??????");

                    // ????????? ????????? ????????? ????????? ???????????? ????????? ?????? ????????? ?????? ?????? ?????? ????????? ??????
                    if(!values.equals("??????")) {
                        String volumezero = values.get(0).replace("L", "");
                        String[] volumes = volumezero.split("~");

                        // ????????? ????????? and??? ???????????? ?????? ??????????????? ?????? ?????????
                        if (volumes[0].equals("")) { // ~5L
                            double maxVolume = Double.parseDouble(volumes[1]);
                            p = cb.and(p, cb.lessThanOrEqualTo(root.get("volume"), maxVolume));
                        } else if(volumes.length == 2) { // 20L~
                            double maxVolume = Double.parseDouble(volumes[1]);
                            double minVolume = Double.parseDouble(volumes[0]);
                            p = cb.and(p, cb.between(root.get("volume"), minVolume, maxVolume));
                        } else { // 5~10L
                            double minVolume = Double.parseDouble(volumes[0]);
                            p = cb.and(p, cb.greaterThanOrEqualTo(root.get("volume"), minVolume));
                        }

                        // ?????? ???????????? ???????????? or??? ???????????? ???????????? ?????? ?????? ???????????? ?????? ?????????
                        for(int i=1, n=values.size(); i<n; i++) {
                            String volumezero2 = values.get(0).replace("L", "");
                            String[] volumes2 = volumezero2.split("~");

                            if (volumes2[0].equals("")) { // ~5L
                                double maxVolume = Double.parseDouble(volumes2[1]);
                                p = cb.or(p, cb.lessThanOrEqualTo(root.get("volume"), maxVolume));
                            } else if(volumes2.length == 2) { // 20L~
                                double maxVolume = Double.parseDouble(volumes2[1]);
                                double minVolume = Double.parseDouble(volumes2[0]);
                                p = cb.or(p, cb.between(root.get("volume"), minVolume, maxVolume));
                            } else { // 5~10L
                                double minVolume = Double.parseDouble(volumes2[0]);
                                p = cb.or(p, cb.greaterThanOrEqualTo(root.get("volume"), minVolume));
                            }
                        }
                    }
                }

                // ???????????? ?????????
                if(filters.get("????????????") != null){
                    List<String> values = (ArrayList<String>) filters.get("????????????");

                    // ????????? ????????? ????????? ????????? ???????????? ????????? ?????? ????????? ?????? ?????? ?????? ????????? ??????
                    if(!values.equals("??????")) {
                        String powerzero = values.get(0).replace("W", "");
                        String[] powers = powerzero.split("~");

                        // ????????? ????????? and??? ???????????? ?????? ??????????????? ?????? ?????????
                        if (powers[0].equals("")) { // ~5??????
                            int maxPower = Integer.parseInt(powers[1]);
                            p = cb.and(p, cb.lessThanOrEqualTo(root.get("power"), maxPower));
                        } else if(powers.length == 2) { // 20??????~
                            int maxPower = Integer.parseInt(powers[1]);
                            int minPower = Integer.parseInt(powers[0]);
                            p = cb.and(p, cb.between(root.get("power"), minPower, maxPower));
                        } else { // 5~10??????
                            int minPower = Integer.parseInt(powers[0]);
                            p = cb.and(p, cb.greaterThanOrEqualTo(root.get("power"), minPower));
                        }

                        // ?????? ???????????? ???????????? or??? ???????????? ???????????? ?????? ?????? ???????????? ?????? ?????????
                        for(int i=1, n=values.size(); i<n; i++) {
                            String powerzero2 = values.get(0).replace("W", "");
                            String[] powers2 = powerzero2.split("~");

                            if (powers2[0].equals("")) { // ~1000W
                                int maxPower = Integer.parseInt(powers2[1]);
                                p = cb.and(p, cb.lessThanOrEqualTo(root.get("power"), maxPower));
                            } else if(powers2.length == 2) { // 20??????~
                                int maxPower = Integer.parseInt(powers2[1]);
                                int minPower = Integer.parseInt(powers2[0]);
                                p = cb.and(p, cb.between(root.get("power"), minPower, maxPower));
                            } else { // 2000W~
                                int minPower = Integer.parseInt(powers2[0]);
                                p = cb.and(p, cb.greaterThanOrEqualTo(root.get("power"), minPower));
                            }
                        }
                    }
                }
                return p;
            }
        };
    }

    /**
    * @Method Name : findAirfryerByPcode
    * @????????? : ?????????
    * @Method ?????? : pcode??? ?????? ?????????????????? ??????
    */
    public Airfryer findAirfryerByPcode(long pcode) {
        Airfryer airfryer = airfryerRepository.findById(pcode).orElseGet(null);
        return airfryer;
    }
    
    /**  
    * @Method Name : findAirfryerPerformanceByPcode  
    * @????????? : ?????????
    * @Method ?????? : pcode??? ?????? ?????????????????? ?????? ??????
    */
    public AirfryerPerformance findAirfryerPerformanceByPcode(long pcode) {
        AirfryerPerformance airfryerPerformance = airfryerPerformanceRepository.findById(pcode).orElse(null);
        return airfryerPerformance;
    }

    /**
    * @Method Name : findLowPriceInfoByPcode
    * @????????? : ?????????
    * @Method ?????? : pcode??? ?????? ???????????? ????????? ????????? ?????? ??????
    */
    public List<LowPriceInfo> findLowPriceInfoByPcode(long pcode) {
        List<LowPriceInfo> lowPriceInfoList = lowPriceInfoRepository.findAllByPcode(pcode).orElse(null);
        return lowPriceInfoList;
    }

    /**
     * @Method Name : findCheckPick
     * @????????? : ?????????
     * @Method ?????? : ?????????????????? ?????????????????? ????????? ?????? ????????? 10??? ?????? ??????
     */
    public List<AirfryerGetRes> findCheckPick() {
        List<AirfryerGetRes> airfryerGetResList = new ArrayList<>();
        List<AirfryerPerformance> airfryerPerformanceGetResList = airfryerPerformanceRepository.findCheckPick();

        for(AirfryerPerformance airfryerPerformance : airfryerPerformanceGetResList) {
            Airfryer airfryer = airfryerRepository.findById(airfryerPerformance.getPcode()).orElseGet(null);
            airfryerGetResList.add(AirfryerGetRes.of(airfryer, airfryerPerformance));
        }
        return airfryerGetResList;
    }

    /**
     * @Method Name : findAllByKeyword
     * @????????? : ?????????
     * @Method ?????? : keyword??? ???????????? ?????????????????? ?????? ??????
     */
    public List<AirfryerGetRes> findAllByKeyword(int page, String keyword) {
        PageRequest pageRequest = PageRequest.of(page, 30, Sort.Direction.DESC, "pcode");
        Page<Airfryer> airfryers = airfryerRepository.findAllByNameContaining(keyword, pageRequest);
        long totalResultCount= airfryerRepository.countByNameContaining(keyword); // ??? ???????????? ???????????? ??????
        List<AirfryerGetRes> airfryerGetResList = new ArrayList<>();
        int size = airfryers.getContent().size();
        for(int i = 0; i < size; i++) {
            Airfryer airfryer = airfryers.getContent().get(i);
            Optional<AirfryerPerformance> airfryerPerformance = airfryerPerformanceRepository.findById(airfryer.getPcode());
            if(airfryerPerformance.isPresent()) {
                airfryerGetResList.add(AirfryerGetRes.ofWithTotalResultCount(airfryer, airfryerPerformance.get(), totalResultCount));
            }
        }

        return airfryerGetResList;
    }

}
