package vn.com.viettel.core.mapper;

import java.util.List;

public interface IMapper<S, D> {
    D toDest(S src);

    S toSrc(D dest);

    List<D> toDest(List<S> srcList);

    List<S> toSrc(List<D> destList);
}
