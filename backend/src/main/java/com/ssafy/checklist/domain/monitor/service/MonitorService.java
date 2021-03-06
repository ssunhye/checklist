package com.ssafy.checklist.domain.monitor.service;

import com.ssafy.checklist.domain.coffeemachine.controller.response.CoffeemachineGetRes;
import com.ssafy.checklist.domain.coffeemachine.entity.Coffeemachine;
import com.ssafy.checklist.domain.coffeemachine.entity.CoffeemachinePerformance;
import com.ssafy.checklist.domain.common.entity.LowPriceInfo;
import com.ssafy.checklist.domain.common.repository.LowPriceInfoRepository;
import com.ssafy.checklist.domain.monitor.controller.response.MonitorGetRes;
import com.ssafy.checklist.domain.monitor.controller.response.MonitorInfoGetRes;
import com.ssafy.checklist.domain.monitor.entity.Monitor;
import com.ssafy.checklist.domain.monitor.entity.MonitorPerformance;
import com.ssafy.checklist.domain.monitor.repository.MonitorPerformanceRepository;
import com.ssafy.checklist.domain.monitor.repository.MonitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

@Service
public class MonitorService {

    @Autowired
    MonitorRepository monitorRepository;

    @Autowired
    LowPriceInfoRepository lowPriceInfoRepository;

    @Autowired
    MonitorPerformanceRepository monitorPerformanceRepository;

    public Monitor findMonitorById(String pcode) {

        return monitorRepository.findMonitorByPcode(Long.parseLong(pcode)).orElse(null);
    }

    public MonitorPerformance findMonitorPerformanceById(String pcode) {
        return monitorPerformanceRepository.findById(Long.parseLong(pcode)).orElse(null);
    }

    public List<LowPriceInfo> findLowPriceById(String pcode) {

        return lowPriceInfoRepository.findAllByPcode(Long.parseLong(pcode)).orElse(null);
    }

    public List<MonitorInfoGetRes> findMonitorByFilters(Map<String, Object> map, Pageable pageRequest) {

        /**
        * @Method Name : findMonitorByFilters
        * @????????? : ?????????
        * @Method ?????? : ????????? ?????? ????????? ????????? ???????????? ???????????? ????????? ??????.
        *               ???????????? ???????????? ?????? ?????? ????????? ???????????? ???????????? ??????.
        *               ??? ????????? ????????? ????????? MonitorInfoGetRes ???????????? ?????? ?????????.
        */

        Specification<Monitor> spec = getMultiFilter(map);

        // ????????? ?????? ?????? ??????
        Page<Monitor> mp = monitorRepository.findAll(spec, pageRequest);
        List<Monitor> mList = mp.getContent();

        // ???????????? ??????
        List<MonitorPerformance> mpList = new LinkedList<>();
        for(int i=0; i< mList.size(); i++) {
            mpList.add(monitorPerformanceRepository.findById(mList.get(i).getPcode()).orElse(null));
            System.out.println("????????? ?????? >>> " + mpList.get(i));
        }

        return MonitorInfoGetRes.of(mList, mpList);
    }

//    public List<MonitorPerformance> findMonitorPerformanceByPcode(long[] pList) {
//        /**
//        * @Method Name : findMonitorPerformanceByPcode
//        * @????????? : ?????????
//        * @Method ?????? : pcode List??? ???????????? ???????????? ????????? List??? ????????? ?????????
//        */
//
//        List<MonitorPerformance> mpList = new LinkedList<>();
//
//        for(int i=0; i<pList.length; i++) {
//            mpList.add(monitorPerformanceRepository.findById(pList[i]).orElse(null));
//        }
//
//        return mpList;
//    }

    @SuppressWarnings({"unused", "unchecked"})
    public Specification<Monitor> getMultiFilter(Map<String, Object> map) {
        /**
        * @Method Name : getMultiFilter
        * @????????? : ?????????
        * @Method ?????? : ????????? ????????? Map?????? ????????? ????????? ???????????? ???????????? ????????? ??????. (?????? ??????)
        */

        return new Specification<Monitor>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Monitor> root, javax.persistence.criteria.CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate p = cb.conjunction();
                List<Predicate> pList = new ArrayList<Predicate>();

                if (map.get("?????????") != null) {
                    List<String> values = (ArrayList<String>) map.get("?????????");

                    // ????????? ????????? ????????? ????????? ???????????? ????????? ?????? ????????? ?????? ?????? ?????? ????????? ??????
                    if(!values.contains("??????")) {
                        String[] prices = values.get(0).split("~");

                        // ????????? ????????? and??? ???????????? ?????? ??????????????? ?????? ?????????
                        if (prices[0].equals("")) {
                            long maxPrice = Long.parseLong(prices[1]+"0000");
                            p = cb.and(p, cb.lessThanOrEqualTo(root.get("price"), maxPrice));
                        } else if(prices[1].equals("")) {
                            long minPrice = Long.parseLong(prices[0]+"0000");
                            p = cb.and(p, cb.greaterThanOrEqualTo(root.get("price"), minPrice));
                        } else {
                            long maxPrice = Long.parseLong(prices[1]+"0000");
                            long minPrice = Long.parseLong(prices[0]+"0000");
                            p = cb.and(p, cb.between(root.get("price"), minPrice, maxPrice));
                        }

                        // ?????? ???????????? ???????????? or??? ???????????? ???????????? ?????? ?????? ???????????? ?????? ?????????
                        for(int i=1, n=values.size(); i<n; i++) {
                            String[] prices2 = values.get(i).split("~");

                            if (prices2[0].equals("")) {
                                long maxPrice2 = Long.parseLong(prices2[1]+"0000");
                                p = cb.or(p, cb.lessThanOrEqualTo(root.get("price"), maxPrice2));
                            } else if(prices2[1].equals("")) {
                                long minPrice2 = Long.parseLong(prices2[0]+"0000");
                                p = cb.or(p, cb.greaterThanOrEqualTo(root.get("price"), minPrice2));
                            } else {
                                long maxPrice2 = Long.parseLong(prices2[1]+"0000");
                                long minPrice2 = Long.parseLong(prices2[0]+"0000");
                                p = cb.or(p, cb.between(root.get("price"), minPrice2, maxPrice2));
                            }
                        }
                    }

                }

                if(map.get("?????? ??????") != null) {
                    List<String> values = (ArrayList<String>) map.get("?????? ??????");

                    if(!values.contains("??????")) {
                        p = cb.and(p, cb.like(root.get("size"), "%"+values.get(0)+"%"));
                        for(int i=1, n=values.size(); i<n; i++) {
                            p = cb.or(p, cb.like(root.get("size"), "%"+values.get(i)+"%"));
                        }
                    }
                }

                if(map.get("?????????") != null) {
                    List<String> values = (ArrayList<String>) map.get("?????????");

                    if(!values.contains("??????")) {
                        p = cb.and(p, cb.like(root.get("pixel"), "%"+values.get(0)+"%"));
                        for(int i=1, n=values.size(); i<n; i++) {
                            p = cb.or(p, cb.like(root.get("pixel"), "%"+values.get(i)+"%"));
                        }
                    }
                }

                if(map.get("?????????") != null) {
                    List<String> values = (ArrayList<String>) map.get("?????????");

                    if(!values.contains("??????")) {
                        p = cb.and(p, cb.equal(root.get("hz"), values.get(0).substring(0, values.get(0).length()-2)));
                        for(int i=1, n=values.size(); i<n; i++) {
                            p = cb.or(p, cb.equal(root.get("hz"), values.get(0).substring(0, values.get(0).length()-2)));
                        }
                    }
                }

                return p;
            }

        };
    }


    public List<MonitorGetRes> findCheckPick() {
        List<MonitorGetRes> monitorGetResList = new ArrayList<>();
        List<MonitorPerformance> monitorPerformances = monitorPerformanceRepository.findCheckPick();

        for(MonitorPerformance monitorPerformance : monitorPerformances) {
            Monitor monitor = monitorRepository.findMonitorByPcode(monitorPerformance.getPcode()).get();
            monitorGetResList.add(MonitorGetRes.of(monitor, monitorPerformance, null));
        }
        return monitorGetResList;
    }

    public List<MonitorGetRes> findAllByKeyword(int page, String keyword) {
        PageRequest pageRequest = PageRequest.of(page, 30, Sort.Direction.DESC, "pcode");
        Page<Monitor> monitors = monitorRepository.findAllByNameContaining(keyword, pageRequest);
        long totalResultCount= monitorRepository.countByNameContaining(keyword); // ??? ???????????? ???????????? ??????
        List<MonitorGetRes> monitorGetResList = new ArrayList<>();
        int size = monitors.getContent().size();
        for(int i = 0; i < size; i++) {
            Monitor monitor = monitors.getContent().get(i);
            Optional<MonitorPerformance> monitorPerformance = monitorPerformanceRepository.findById(monitor.getPcode());
            if(monitorPerformance.isPresent()) {
                monitorGetResList.add(MonitorGetRes.ofWithTotalResultCount(monitor, monitorPerformance.get(), null, totalResultCount));
            }
        }

        return monitorGetResList;
    }
}
