
import ra.entity.Product;
import ra.util.ProductFilter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static List<Product> products = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("1. Danh sách sản phẩm");
            System.out.println("2. Thêm mới sản phẩm");
            System.out.println("3. Cập nhật sản phẩm");
            System.out.println("4. Xóa sản phẩm theo ID");
            System.out.println("5. Tìm sản phẩm theo tên");
            System.out.println("6. Lọc sản phẩm theo điều kiện");
            System.out.println("7. Sắp xếp sản phẩm theo giá");
            System.out.println("8. Thoát");
            System.out.print("Chọn chức năng: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1 -> listProducts();
                case 2 -> addProduct();
                case 3 -> updateProduct();
                case 4 -> deleteProductById();
                case 5 -> searchProductByName();
                case 6 -> filterProducts();
                case 7 -> sortProducts();
                case 8 -> System.exit(0);
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        }while (choice !=8);
    }

    private static void sortProducts() {
        System.out.println("1. Sắp xếp tăng dần");
        System.out.println("2. Sắp xếp giảm dần");
        System.out.print("Chọn điều kiện sắp xếp: ");
        int sortChoice = scanner.nextInt();
        if (sortChoice == 1) {
            products.sort(Comparator.comparing(Product::getPrice));
        } else {
            products.sort(Comparator.comparing(Product::getPrice).reversed());
        }
        System.out.println("Sắp xếp thành công!");
    }

    private static void filterProducts() {
        System.out.println("1. Lọc theo giá");
        System.out.println("2. Lọc theo tên");
        System.out.print("Chọn điều kiện lọc: ");
        int filterChoice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        ProductFilter filter;
        if (filterChoice == 1) {
            System.out.print("Nhập giá tối thiểu: ");
            float minPrice = scanner.nextFloat();
            System.out.print("Nhập giá tối đa: ");
            float maxPrice = scanner.nextFloat();
            filter = product -> product.getPrice() >= minPrice && product.getPrice() <= maxPrice;
        } else {
            System.out.print("Nhập tên sản phẩm cần tìm: ");
            String name = scanner.nextLine();
            filter = product -> product.getProductName().toLowerCase().contains(name.toLowerCase());
        }
        List<Product> filteredProducts = products.stream()
                .filter(filter::filter)
                .collect(Collectors.toList());
        if (filteredProducts.isEmpty()) {
            System.out.println("Không tìm thấy sản phẩm nào!");
        } else {
            filteredProducts.forEach(Product::displayData);
        }
    }

    private static void searchProductByName() {
        System.out.print("Nhập tên sản phẩm cần tìm: ");
        String name = scanner.next();
        List<Product> foundProducts = products.stream()
                .filter(product -> product.getProductName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
        if (foundProducts.isEmpty()) {
            System.out.println("Không tìm thấy sản phẩm nào!");
        } else {
            foundProducts.forEach(Product::displayData);
        }
    }

    private static void deleteProductById() {
        System.out.print("Nhập ID sản phẩm cần xóa: ");
        int id = scanner.nextInt();
        products.removeIf(product -> product.getProductId() == id);
        System.out.println("Xóa sản phẩm thành công!");
    }

    private static void updateProduct() {
        System.out.print("Nhập ID sản phẩm cần cập nhật: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        Product product = products.stream()
                .filter(p -> p.getProductId() == id)
                .findFirst()
                .orElse(null);
        if (product != null) {
            product.inputData(scanner);
            System.out.println("Cập nhật thành công!");
        } else {
            System.out.println("Sản phẩm không tồn tại!");
        }
    }

    private static void listProducts() {
        products.forEach(Product::displayData);
    }

    private static void addProduct() {
        Product product = new Product();
        product.inputData(scanner);
        products.add(product);
    }
}
