package ra.util;

import ra.entity.Product;

@FunctionalInterface
public interface ProductFilter {
    boolean filter(Product product);
}
