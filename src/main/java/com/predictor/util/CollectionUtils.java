package com.predictor.util;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class CollectionUtils {

    public static <T>Stream<T> safeStream(List<T> list){
       return  Objects.nonNull(list)? list.stream() : Stream.empty();
    }

}
