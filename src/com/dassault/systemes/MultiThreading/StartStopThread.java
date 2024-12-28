package src.com.dassault.systemes.MultiThreading;

public class StartStopThread extends Thread {
    private volatile boolean isDownloading = true; // Ensuring visibility in multithreaded environment

    public void run() {
        for (int i = 1; i <= 10; i++) {
            if (!isDownloading) {
                System.out.println("Download canceled.");
                return; // Stop the thread if the download is canceled
            }
            try {
                Thread.sleep(1000); // Simulating the download process
                System.out.println("Downloading... " + i * 10 + "%");
            } catch (InterruptedException e) {
                System.out.println("Download interrupted.");
                return; // Exit the thread if interrupted
            }
        }
        System.out.println("Download completed.");
    }

    // Simulate another process that tracks some other data
    public void trackFromWebPage() throws InterruptedException {
        System.out.println("Tracking data from webpage...");
        Thread.sleep(5000);  // Simulate 5 seconds of tracking process
        System.out.println("Done with Tracking Data....!");
    }

    // Method to stop the download gracefully
    public void stopDownload() {
        isDownloading = false;
    }

    public static void main(String[] args) throws InterruptedException {
        StartStopThread downloadThread = new StartStopThread();
        downloadThread.start(); // Start the download thread

        // Simulate some other process (like webpage tracking)
        downloadThread.trackFromWebPage();

        // After tracking is done, check if the download thread is still running
        if (downloadThread.isAlive()) {
            System.out.println("Download is still running. Cancelling the download...");
            downloadThread.stopDownload(); // Gracefully stop the download
        } else {
            System.out.println("Download already completed or canceled.");
        }
    }
}
