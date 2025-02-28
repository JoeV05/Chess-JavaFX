import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;

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
        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int boardX = Math.floorDiv((int) event.getSceneX(), 93);
                int boardY = Math.floorDiv((int) event.getSceneY(), 93);

                System.out.println("Actual (x, y): " + event.getSceneX() + " " + event.getSceneY());
                System.out.println("Board (x, y): " + boardX + " " + boardY);

                System.out.print("\n");
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle(GAME_TITLE);
        this.draw();
    }

    private void draw()
    {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        this.drawBoard(gc);
        this.drawPieces(gc);
    }

    private void drawBoard(GraphicsContext gc)
    {
        gc.setFill(Color.GREY);

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

    private void drawPieces(GraphicsContext gc)
    {
        Piece[][] board = Game.getGame().getBoard();

        for (int y = 0; y < 8; y++)
        {
            for (int x = 0; x < 8; x++)
            {
                Piece p = board[y][x];
                if (p != null)
                {
                    Image pieceSprite = board[y][x].getSprite();
                    gc.drawImage(pieceSprite, x * GRID_CELL_SIZE, y * GRID_CELL_SIZE, GRID_CELL_SIZE, GRID_CELL_SIZE);
                }
            }
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
