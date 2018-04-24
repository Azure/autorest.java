package fixtures.server;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


public class RunServerListener extends RunListener {
    private Process serverProcess;
    private Thread readerThread;

    @Override
    public void testRunStarted(Description description) throws Exception {
        ProcessBuilder pb = new ProcessBuilder("node", "../../node_modules/@microsoft.azure/autorest.testserver")
                .redirectErrorStream(true);

        serverProcess = pb.start();

        CountDownLatch latch = new CountDownLatch(1);
        readerThread = new Thread(() -> {
            try {
                Path outPath = Paths.get("testserver.log");
                Files.deleteIfExists(outPath);

                BufferedReader reader = new BufferedReader(new InputStreamReader(serverProcess.getInputStream()));
                BufferedWriter writer = Files.newBufferedWriter(outPath);

                String line = reader.readLine();
                writer.append(line);
                writer.newLine();
                LoggerFactory.getLogger(getClass()).info(line);

                latch.countDown();

                while ((line = reader.readLine()) != null) {
                    writer.append(line);
                    writer.newLine();
                }
                reader.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        readerThread.start();

        latch.await(2, TimeUnit.SECONDS);
        super.testRunStarted(description);
    }

    @Override
    public void testRunFinished(Result result) throws Exception {
        serverProcess.destroy();
        readerThread.join();

        super.testRunFinished(result);
    }
}
