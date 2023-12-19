package JavaLambdaStreams.test;

import JavaLambdaStreams.model.Component;

import java.util.*;
import java.util.stream.Collectors;

public final class StreamsDemo {

    private final List<Component> components;

    public StreamsDemo(List<Component> components) {
        this.components = components;
    }

    // Sort the list of computer components by Category, Name, and Brand
    // Print only the first 10 components of this sorted list
    public List<Component> printTop10SortedComponentsByCategoryNameAndBrand() {
        return components.stream()
                // 根据指定的Property来构建Comparator比较器
                // .sorted(ComparatorHelper.buildComparatorByCategoryNameAndBrand())
                .limit(10)
                .collect(Collectors.toList());
    }

    // Compute and print the average price of all the computer components
    public double printAveragePriceOfAllComponents() {
        return components.stream()
                .mapToDouble(Component::getPrice)
                .average()
                .orElse(Double.NaN);
    }

    // Compute and print the average price of the CPU components only
    public double printAveragePriceOfCpuComponents() {
        return components.stream()
                .filter(component -> component.getCategory().equals("CPU"))
                .mapToDouble(Component::getPrice)
                .average()
                .orElse(Double.NaN);
    }

    // Print the details of the cheapest component in the store
    public double printCheapestComponent() {
        OptionalDouble result = components.stream()
                .mapToDouble(Component::getPrice)
                .min();
        return result.isPresent() ? result.getAsDouble() : 0D;
    }

    // Print the details of the most expensive components of each category in the store
    public Map<String, Optional<Component>> printMostExpensiveComponentsOfEachCategory() {
        // 自定义根据价格来判断的比较器
        Comparator<Component> priceComparator = new Comparator<Component>() {
            @Override
            public int compare(Component component1, Component component2) {
                return (int) (component1.getPrice() - component2.getPrice());
            }
        };
        return components.stream().collect(Collectors.groupingBy(Component::getCategory,
                Collectors.maxBy(priceComparator)));
    }

    // Print a report showing the number of components grouped by Component Category
    public Map<String, Long> reportNumberOfComponentsByCategory() {
        return components.stream().collect(Collectors.groupingBy(Component::getCategory, Collectors.counting()));
    }

    // Print a report showing the number of components grouped by Component Category, Brand
    public Map<String, Map<String, Long>> reportNumberOfComponentsByCategoryAndBrand() {
        return components.stream().collect(Collectors.groupingBy(Component::getCategory,
                Collectors.groupingBy(Component::getBrand, Collectors.counting())));
    }
}
