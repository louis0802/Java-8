import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] aa){
        Task task1 = new Task("Read Version Control with Git book", TaskType.READING, LocalDate.of(2015, Month.JULY, 1)).addTag("git").addTag("reading").addTag("books");

        Task task2 = new Task("Read Java 8 Lambdas book", TaskType.READING, LocalDate.of(2015, Month.JULY, 2)).addTag("java8").addTag("reading").addTag("books");

        Task task3 = new Task("Write a mobile application to store my tasks", TaskType.CODING, LocalDate.of(2015, Month.JULY, 3)).addTag("coding").addTag("mobile");

        Task task4 = new Task("Write a blog on Java 8 Streams", TaskType.WRITING, LocalDate.of(2015, Month.JULY, 4)).addTag("blogging").addTag("writing").addTag("streams");

        Task task5 = new Task("Read Domain Driven Design book", TaskType.READING, LocalDate.of(2015, Month.JULY, 5)).addTag("ddd").addTag("books").addTag("reading");

        List tasks = Arrays.asList(task1, task2, task3, task4, task5);

        findAllReadingTitlesSortedByCreationDate(tasks).forEach(i -> System.out.println(i));

        limitByCreatedDay(3,tasks).forEach(System.out::println);

        allDistinctTags(tasks).forEach(System.out::println);

        System.out.println(isAllReadingTasksWithTagBooks(tasks));

        System.out.println(joinAllTaskTitles(tasks));

        IntStream.range(0,10).forEach(System.out::print); //Exclude upper bound 10

        System.out.println();

        IntStream.rangeClosed(1,10).forEach(System.out::print); //Allow upper bound 10

        System.out.println();

        IntStream.rangeClosed(1,10).parallel().forEach(System.out::print);
        System.out.println();

        LongStream infiniteStream = LongStream.iterate(1, el -> el + 1);
        infiniteStream.filter(el -> el % 2 == 0).limit(10).forEach(System.out::println);

        String[] tags = new String[]{"Louis","XPS","Thinkpad","Java8"};
        Arrays.stream(tags).map(String::toUpperCase).forEach(System.out::println);


    }

    public static List<String> findAllReadingTitlesSortedByCreationDate(List<Task> tasks){
        List<String> collect = tasks.stream()
                .filter(i -> i.getType() == TaskType.READING)
                .sorted(Comparator.comparing(Task::getCreatedOn))
                .map(t -> t.getTitle())
                .collect(toList());
        return collect;
    }

    public static List<String> limitByCreatedDay(int limit,List<Task> tasks){
        return tasks.stream().sorted(Comparator.comparing(Task::getCreatedOn)).limit(limit).map(task -> task.getTitle()).collect(toList());
    }
    private static List<String> allDistinctTags(List<Task> tasks){
        return tasks.stream().flatMap( task -> task.getTags().stream()).distinct().collect(toList());
    }
    private static boolean isAllReadingTasksWithTagBooks(List<Task> tasks){
        return tasks.stream().filter(task -> task.getType() == TaskType.READING).allMatch(task -> task.getTags().contains("Books"));
    }
    private static String joinAllTaskTitles(List<Task> tasks){
        return tasks.stream().map( task -> task.getTitle()).reduce( (first,second) -> first+" *** " +second).get();
    }
}
