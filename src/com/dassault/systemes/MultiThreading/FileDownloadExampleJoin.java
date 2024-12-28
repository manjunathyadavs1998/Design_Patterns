package src.com.dassault.systemes.MultiThreading;

//public class FileDownloadExampleJoin {
public class FileDownloadExampleJoin {

    static class FileDownloadThread extends Thread {
        private String fileName;

        // Constructor to set the file name for each download thread
        public FileDownloadThread(String fileName) {
            this.fileName = fileName;
        }

        // Simulating file download by sleeping for a random time
        @Override
        public void run() {
            try {
                System.out.println("Downloading: " + fileName);
                // Simulate file download by sleeping for a random time between 1 and 3 seconds
                int downloadTime = (int) (Math.random() * 3) + 1;
                Thread.sleep(downloadTime * 1000);
                System.out.println("Download complete for: " + fileName);
            } catch (InterruptedException e) {
                System.out.println("Download interrupted for: " + fileName);
            }
        }
    }

    public static void main(String[] args) {
        // Creating multiple threads for downloading files
        Thread file1 = new FileDownloadThread("file1.txt");
        Thread file2 = new FileDownloadThread("file2.txt");
        Thread file3 = new FileDownloadThread("file3.txt");

        // Starting all download threads
        file1.start();
        file2.start();
        file3.start();

        try {
            // Wait for all download threads to finish before processing the files
            file1.join();
            file2.join();
            file3.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted while waiting.");
        }

        // Once all download threads finish, proceed with further processing
        System.out.println("All files downloaded. Now processing the files.");
    }
}

