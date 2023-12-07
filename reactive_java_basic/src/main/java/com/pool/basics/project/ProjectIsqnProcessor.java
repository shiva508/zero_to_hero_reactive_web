package com.pool.basics.project;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.pool.Util;
import reactor.core.publisher.Flux;

public class ProjectIsqnProcessor {
    public static void main(String[] args) {
        List<IsqnData> isqnDatas = new ArrayList<>();
        isqnDatas.add(new IsqnData(1l, "A"));
        isqnDatas.add(new IsqnData(2l, "B"));
        isqnDatas.add(new IsqnData(4l, "A"));

        Flux.create(sink -> {
            Map<String, Set<Long>> dataa = isqnDatas.stream()
                    .collect(Collectors.groupingBy(IsqnData::getDirection,
                            Collectors.mapping(IsqnData::getIsqnid, Collectors.toSet())));

        });
        Flux.fromIterable(isqnDatas)
                .collectMultimap(IsqnData::getDirection, IsqnData::getIsqnid)
                .map(ProjectIsqnProcessor::processData)
                .doOnNext(Flux::collectList)
                .subscribe(Util.subscriber());
    }

    private static Flux<Long> processData(Map<String, Collection<Long>> data) {

        return Flux.create(sink -> {
            sink.next(12l);
            sink.complete();
        });

    }
}
