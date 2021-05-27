import com.kisen.mms.wx.api.WXServerApiWrapper;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2020/3/11
 */
public class Main {
  private static final String AppID = "wxa1b06932c4d64211";
  private static final String AppSecret = "4dce8a94b8874eaf93aa32245592d379";

  public static void main(String[] argv) throws InterruptedException {
    WXServerApiWrapper wxServerApiWrapper = new WXServerApiWrapper(AppID, AppSecret);
    Disposable disposable =
        wxServerApiWrapper
            .authorize("www.baidu.com", "123")
            .subscribe(
                new Consumer<ResponseBody>() {
                  @Override
                  public void accept(ResponseBody responseBody) throws Exception {
                    System.out.println(responseBody.toString());
                  }
                },
                new Consumer<Throwable>() {
                  @Override
                  public void accept(Throwable throwable) throws Exception {
                    System.out.println(throwable.getMessage());
                  }
                });
    while (!disposable.isDisposed()) {
      Thread.sleep(1000);
    }
    System.out.println("exit ...");
  }
}
