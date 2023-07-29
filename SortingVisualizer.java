import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class SortingVisualizer extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int NUM_BARS = 100;
    private static final int BAR_WIDTH = WIDTH / NUM_BARS;
    private static final int MAX_BAR_HEIGHT = HEIGHT - 50;
    private static final int DELAY = 10;

    private int[] array;
    private boolean sorting;

    public SortingVisualizer() {
        array = new int[NUM_BARS];
        sorting = false;

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sorting Algorithm Visualizer");
        generateRandomArray();
    }

    private void generateRandomArray() {
        Random rand = new Random();
        for (int i = 0; i < NUM_BARS; i++) {
            array[i] = rand.nextInt(MAX_BAR_HEIGHT);
        }
    }

    private void bubbleSort() {
        for (int i = 0; i < NUM_BARS - 1; i++) {
            for (int j = 0; j < NUM_BARS - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                    // Update the visualization
                    repaint();
                    sleep(DELAY);
                }
            }
        }
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (int i = 0; i < NUM_BARS; i++) {
            int x = i * BAR_WIDTH;
            int height = array[i];
            int y = MAX_BAR_HEIGHT - height;
            g.setColor(Color.PINK);
            g.fillRect(x, y, BAR_WIDTH, height);
        }
    }

    public void startSorting() {
        if (!sorting) {
            sorting = true;
            new Thread(() -> {
                bubbleSort();
                sorting = false;
            }).start();
        }
    }

    public static void main(String[] args) {
        SortingVisualizer visualizer = new SortingVisualizer();
        JButton sortButton = new JButton("START SORTING");
        sortButton.addActionListener(e -> visualizer.startSorting());

        visualizer.setLayout(new BorderLayout());
        visualizer.add(sortButton, BorderLayout.SOUTH);
        visualizer.pack();
        visualizer.setVisible(true);
    }
}

