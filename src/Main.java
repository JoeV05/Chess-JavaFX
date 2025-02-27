import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class Main extends Application
{
    public static final int GRID_CELL_SIZE = 93;
    public static final String GAME_TITLE = "Chess";
    public static final int CANVAS_WIDTH = 744;
    public static final int CANVAS_HEIGHT = 744;
    private Canvas canvas;

    @Override
    public void start(Stage primaryStage)
    {
        BorderPane root = new BorderPane();

        this.canvas = new Canvas(CANVAS_WIDTH, CANVAS_WIDTH);

        root.setCenter(this.canvas);

        Scene scene = new Scene(root, CANVAS_WIDTH, CANVAS_HEIGHT);

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle(GAME_TITLE);
        this.draw();
    }

    private void draw()
    {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);

        for (int y = 0; y < 8; y++)
        {
            for (int x = 0; x < 8; x++)
            {
                if ((y % 2 == 0 && x % 2 == 1) || (y % 2 == 1 && x % 2 == 0))
                {
                    gc.fillRect(x * GRID_CELL_SIZE, y * GRID_CELL_SIZE, GRID_CELL_SIZE, GRID_CELL_SIZE);
                }
            }
        }
    }

    public static void main(String[] args) {
        Game.getGame().printBoard();
        launch(args);
    }
}
