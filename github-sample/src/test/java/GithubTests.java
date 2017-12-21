import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import javax.json.JsonObject;
import java.io.IOException;

/**
 * Created by uasso on 18/12/2017.  из видео 9.6
 *  Сервис Github предоставляет работу с API по REST. ДЛя этого есть спец бибки для работы на java,
 *  мы используем jcabi-github
 *  В профиле гитхаба генерируем спец accesstoken и исп в кач ве параметров. Вуаля!
 *  Самое сложное здесь изспользование в итераторе ImmutableMap
 */
public class GithubTests {

    @Test
  public void testCommits() throws IOException {
        Github github = new RtGithub("1cac1dae88ca5a16ba84fc1e482e8a37f683174d");  // taken from settings/tokens
        Repo repo = github.repos().get(new Coordinates.Simple("Svetus927", "javahomework"));
    //    Issue issue = repo.issues().create("How are you?", "Please tell me...");
        RepoCommits commits = repo.commits();
        // здесь используется immutableMap, кот до этого в  курсе не рассматривались
        for (RepoCommit rc: commits.iterate(new ImmutableMap.Builder<String, String>().build() ) ) {

            System.out.println(new RepoCommit.Smart(rc).message()) ;

        }
    }
}
