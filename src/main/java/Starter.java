import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Starter {
    public static void main(String[] args) {
        try {
            RunProcess(new String[]{"bash"});
            RunProcess(new String[]{"bash", "service", "ssh", "start"});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void RunProcess(final String[] commands) throws Exception {
        new Thread() {
            @Override
            public void run() {
                super.run();
                Runtime runtime = Runtime.getRuntime();
                Process process;
                try {
                    process = runtime.exec(commands);
                    InputStream is = process.getErrorStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader bf = new BufferedReader(isr);
                    String line;
                    while ((line = bf.readLine()) != null) {
                        System.out.println(line);
                    }
                    int exitCode = process.waitFor();
                } catch (IOException e) {
                    e.printStackTrace();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
}