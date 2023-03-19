package ass1.src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This is just a demo for you, please run it on JDK17 (some statements may be not allowed in lower
 * version). This is just a demo, and you can extend and implement functions based on this demo, or
 * implement it in a different way.
 */
public class OnlineCoursesAnalyzer {

  List<Course> courses = new ArrayList<>();

  public OnlineCoursesAnalyzer(String datasetPath) {
    BufferedReader br = null;
    String line;
    try {
      br = new BufferedReader(new FileReader(datasetPath, StandardCharsets.UTF_8));
      br.readLine();
      while ((line = br.readLine()) != null) {
        String[] info = line.split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)", -1);
        Course course = new Course(info[0], info[1], new Date(info[2]), info[3], info[4], info[5],
            Integer.parseInt(info[6]), Integer.parseInt(info[7]), Integer.parseInt(info[8]),
            Integer.parseInt(info[9]), Integer.parseInt(info[10]), Double.parseDouble(info[11]),
            Double.parseDouble(info[12]), Double.parseDouble(info[13]),
            Double.parseDouble(info[14]),
            Double.parseDouble(info[15]), Double.parseDouble(info[16]),
            Double.parseDouble(info[17]),
            Double.parseDouble(info[18]), Double.parseDouble(info[19]),
            Double.parseDouble(info[20]),
            Double.parseDouble(info[21]), Double.parseDouble(info[22]));
        courses.add(course);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (br != null) {
        try {
          br.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  //1
  public Map<String, Integer> getPtcpCountByInst() {
    /**
     * This method returns a <institution, count> map, where the key is the institution while the value is the
     * total number of participants who have accessed the courses of the institution.
     * The map should be sorted by the alphabetical order of the institution.
     */
    //排序后于grouping by
    Map<String, Integer> map = this.courses.stream()
        .collect(Collectors.groupingBy(course -> course.institution,
            Collectors.summingInt(course -> course.participants)));

    Map<String, Integer> sortedMap = map.entrySet().stream()
        .sorted(Map.Entry.comparingByKey())
        .collect(
            Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, null, LinkedHashMap::new));

    return sortedMap;
  }

  //2
  public Map<String, Integer> getPtcpCountByInstAndSubject() {
    /**
     * This method returns a <institution-course Subject, count> map, where the key is the string
     * concatenating the Institution and the course Subject (without quotation marks) using '-'
     * while the value is the total number of participants in a course Subject of an institution.
     * The map should be sorted by descending order of count (i.e., from most to least participants). If two
     * participants have the same count, then they should be sorted by the alphabetical order of the
     * institution-course Subject
     */
    Map<String, Integer> map = this.courses.stream()
        .collect(Collectors.groupingBy(course -> course.institution.concat('-' + course.subject),
            Collectors.summingInt(course -> course.participants)));

    Map<String, Integer> sortedMap = map.entrySet().stream()
        .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()
            .thenComparing(Map.Entry.comparingByKey()))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, null,
            LinkedHashMap::new)); //不会出现重复键值对
    return sortedMap;
  }

  //3
  public Map<String, List<List<String>>> getCourseListOfInstructor() {
    /**
     * An instructor may be responsible for multiple courses, including independently responsible courses and codeveloped courses.
     * This method returns a <Instructor, [[course1, course2,...],[coursek,coursek+1,...]]>
     * map, where the key is the name of the instructor (without quotation marks) while the value is a list
     * containing 2-course lists, where List 0 is the instructor's independently responsible courses, if s/he has no
     * independently responsible courses, this list also needs to be created, but with no elements.
     * List 1 is the instructor's co-developed courses, if there are no co-developed courses, do the same as List 0.
     *
     * Note that：
     * the course title (without quotation marks) should be sorted by alphabetical order in the list,
     * and the case of
     * identical names should be treated as the same person.
     */
    return null;
  }

  //4
  public List<String> getCourses(int topK, String by) {
    /**
     * his method returns the top K courses (parameter topK) by the given criterion (parameter by). Specifically,
     * by="hours": the results should be courses sorted by descending order of Total Course Hours
     * (Thousands) (from the longest course to the shortest course).
     * by="participants": the results should be courses sorted by descending order of the number of
     * the Participants (Course Content Accessed) (from the most to the least).
     * Note that the results should be a list of Course titles. If two courses have the same total Course hours or
     * participants, then they should be sorted by alphabetical order of their titles. The same course title can only
     * occur once in the list.
     */
    return null;
  }

  //5
  public List<String> searchCourses(String courseSubject, double percentAudited,
      double totalCourseHours) {
    /**
     * This method searches courses based on three criteria:
     * courseSubject: Fuzzy matching is supported and case insensitive. If the inputcourseSubject is
     * "science", all courses whose course subject includes "science" or "Science" or whatever (case
     * insensitive) meet the criteria.
     * percentAudited: the percent of the audited should >= percentAudited
     * totalCourseHours: the Total Course Hours (Thousands) should <= totalCourseHours
     * Note that the results should be a list of course titles that meet the given criteria, and sorted by alphabetical
     * order of the titles. The same course title can only occur once in the list
     */
    return null;
  }

  //6
  public List<String> recommendCourses(int age, int gender, int isBachelorOrHigher) {
    /**
     * This method recommends 10 courses based on the following input parameter:
     * age: age of the user
     * gender: 0-female, 1-male
     * isBachelorOrHigher: 0-Not get bachelor degree, 1- Bachelor degree or higher
     * First, calculate the average Median Age, average % Male, and average % Bachelor's Degree or
     * Higher for each course. Note that Course Number is the unique id of each course;
     * Secondly, the following formula:
     * $similarity value= (age -average Median Age)^2 + (gender100 - average Male)^2 + (isBachelorOrHigher100
     * - average Bachelor's Degree or Higher)^2$
     * is used to calculate the similarity between the characteristics of the input user and the characteristics of
     * each course's participants. The higher the similarity, the smaller the value;
     * Finally, return the top 10 courses with the smallest similarity value.
     * Note that the results should be a list of course titles. A Course Number may correspond to different
     * course titles, please return the course title with the latest Launch Date and the same course
     * title can only occur once in the list. The courses should be sorted by their similarity values. If two courses
     * have the same similarity values, then they should be sorted by alphabetical order of their titles.
     */
    return null;
  }

}

class Course {

  String institution;
  String number;
  Date launchDate;
  String title;
  String instructors;
  String subject;
  int year;
  int honorCode;
  int participants;
  int audited;
  int certified;
  double percentAudited;
  double percentCertified;
  double percentCertified50;
  double percentVideo;
  double percentForum;
  double gradeHigherZero;
  double totalHours;
  double medianHoursCertification;
  double medianAge;
  double percentMale;
  double percentFemale;
  double percentDegree;

  public Course(String institution, String number, Date launchDate,
      String title, String instructors, String subject,
      int year, int honorCode, int participants,
      int audited, int certified, double percentAudited,
      double percentCertified, double percentCertified50,
      double percentVideo, double percentForum, double gradeHigherZero,
      double totalHours, double medianHoursCertification,
      double medianAge, double percentMale, double percentFemale,
      double percentDegree) {
    this.institution = institution;
    this.number = number;
    this.launchDate = launchDate;
    if (title.startsWith("\"")) {
      title = title.substring(1);
    }
    if (title.endsWith("\"")) {
      title = title.substring(0, title.length() - 1);
    }
    this.title = title;
    if (instructors.startsWith("\"")) {
      instructors = instructors.substring(1);
    }
    if (instructors.endsWith("\"")) {
      instructors = instructors.substring(0, instructors.length() - 1);
    }
    this.instructors = instructors;
    if (subject.startsWith("\"")) {
      subject = subject.substring(1);
    }
    if (subject.endsWith("\"")) {
      subject = subject.substring(0, subject.length() - 1);
    }
    this.subject = subject;
    this.year = year;
    this.honorCode = honorCode;
    this.participants = participants;
    this.audited = audited;
    this.certified = certified;
    this.percentAudited = percentAudited;
    this.percentCertified = percentCertified;
    this.percentCertified50 = percentCertified50;
    this.percentVideo = percentVideo;
    this.percentForum = percentForum;
    this.gradeHigherZero = gradeHigherZero;
    this.totalHours = totalHours;
    this.medianHoursCertification = medianHoursCertification;
    this.medianAge = medianAge;
    this.percentMale = percentMale;
    this.percentFemale = percentFemale;
    this.percentDegree = percentDegree;
  }
}