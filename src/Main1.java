import java.io.File;
import java.util.Objects;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main1 {
    static long totalTime = 0;
    static TreeSet<String> videos = new TreeSet<>();

    //how many second do you want to spend?
    static int estimatedLearningSecond = 60 * 60;

    public static void main(String[] args) {
//        File f = new File("/Users/mustafaatush/Google Drive/01 Dart入门实战教程/");
        File f = new File("/Users/mustafaatush/Google Drive/Flutter仿京东商城项目实战视频教程（IT营大地）-赞助/01 Flutter基础 Flutter Native 教程（50讲）");
        if (f.isDirectory()) {
            listFilesForFile(f);
        } else if (f.isFile()) {
            System.out.println("this is a file");

        }

        System.out.println("total minute is " + totalTime / 60);

        showFormalizedTime(totalTime);
        System.out.println(videos);
        learnEpisodes(estimatedLearningSecond);

    }

    private static void learnEpisodes(int estimatedLearningSecond) {
        long learnEpisodeTotalTime = 0;
        TreeSet<String> learnEpisodesTitle = new TreeSet<>();
        for (String videoTitle : videos) {
            long time = getTime(videoTitle);
            learnEpisodeTotalTime += time;
            learnEpisodesTitle.add(videoTitle);

            if (learnEpisodeTotalTime >= estimatedLearningSecond) {
                if (totalTime <= estimatedLearningSecond + 20 * 60) {
                    showFormalizedTime(learnEpisodeTotalTime);
                    System.out.println(
                            " estimated file is " + videoTitle);
                } else {
                    showFormalizedTime(learnEpisodeTotalTime - getTime(videoTitle));
                    learnEpisodesTitle.pollLast();
                    System.out.println(
                            " estimated file is " + learnEpisodesTitle.last());
                }
                break;
            }
        }


    }

    private static void showFormalizedTime(long time) {
        System.out.println("total time is " + time / 60 / 60 + " h, " + time / 60 % 60 + " m, " + time % 60 + " s");
    }


    static void listFilesForFile(File folder) {

        for (File file :
                Objects.requireNonNull(folder.listFiles())) {
            if (file.isDirectory()) {
                listFilesForFile(file);
                if (getTime(file.getName()) > 0) {
                    videos.add(file.getName() + "   " + getTime(file.getName()) + " s");
                    long time = getTime(file.getName());
                    totalTime += time;

                }
            }
        }

    }

    static int getTime(String name) {

        Pattern compile = Pattern.compile("\\d+分\\d+秒|\\d+分");
        Matcher matcher = compile.matcher(name);
        int totalSecond = 0;
        int secondInt = 0;
        while (matcher.find()) {
            String group = matcher.group();
            int minuteIndex = group.indexOf("分");
            String minute = group.substring(0, minuteIndex);
            int minuteInt = Integer.valueOf(minute.trim());
            if (group.indexOf("秒") > 0) {
                String second = group.substring(minuteIndex + 1, group.length() - 1);
                secondInt = Integer.valueOf(second.trim());
            }
            totalSecond = minuteInt * 60 + secondInt;

        }
        return totalSecond;
    }
}
