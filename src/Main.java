import com.sun.prism.impl.ps.CachingEllipseRep;
import javafx.application.Application;
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
    public static final String GAME_TITLE = "Chess";
    public static final int CANVAS_SIZE = 744;
    //public static final int GRID_CELL_SIZE = 93;
    public static final int GRID_CELL_SIZE = CANVAS_SIZE/Game.BOARD_SIZE;

    private static Main main;
    private Canvas canvas;

    @Override
    public void init()
    {
        main = this;
    }

    @Override
    public void start(Stage primaryStage)
    {
        BorderPane root = new BorderPane();

        this.canvas = new Canvas(CANVAS_SIZE, CANVAS_SIZE);

        root.setCenter(this.canvas);

        Scene scene = new Scene(root, CANVAS_SIZE, CANVAS_SIZE);
        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, this::handle);

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle(GAME_TITLE);
        this.draw();
    }

    private void handle(MouseEvent event)
    {
        int boardX = Math.floorDiv((int) event.getSceneX(), GRID_CELL_SIZE);
        int boardY = Math.floorDiv((int) event.getSceneY(), GRID_CELL_SIZE);

        System.out.println("Actual (x, y): " + event.getSceneX() + " " + event.getSceneY());
        System.out.println("Board (x, y): " + boardX + " " + boardY);

        Piece p = Game.getGame().getBoard()[boardY][boardX];
        System.out.println("Piece: " + p);

        System.out.print("\n");

        Game.getGame().handle(boardX, boardY);

        this.draw();
    }

    private void draw()
    {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        this.drawBoard(gc);
        this.drawPieces(gc);

        this.drawHighlights(Game.getGame().getSelected(), gc);
    }

    private void drawHighlights(Piece p, GraphicsContext gc)
    {
        if (p != null)
        {
            int x = p.getX();
            int y = p.getY();
            Coordinate coordinate = p.getLocation();

            gc.setFill(Color.STEELBLUE);

            if (p instanceof VectorPiece)
            {
                MoveVector[] moves = Game.getGame().getPossibleVectorMoves((VectorPiece) p);

                for (int i = 0; i < moves.length; i++)
                {
                    if (moves[i] != null)
                    {
                        Coordinate highlightCoordinates = moves[i].applyVector(coordinate);
                        int highlightX = highlightCoordinates.getX() * GRID_CELL_SIZE + (GRID_CELL_SIZE/4);
                        int highlightY = highlightCoordinates.getY() * GRID_CELL_SIZE + (GRID_CELL_SIZE/4);

                        gc.fillOval(highlightX, highlightY, GRID_CELL_SIZE/2, GRID_CELL_SIZE/2);
                    }
                }
            } else
            {
                MoveDirection[] moves = Game.getGame().getPossibleDirectionalMoves((DirectionPiece) p);
                System.out.println(moves.length);
                for (int i = 0; i < moves.length; i++)
                {
                    if (moves[i] != null)
                    {
                        MoveDirection move = moves[i];
                        System.out.println(p + " can move up to " + move.getDistance() + " " + move.getDirection());
                    }
                }
            }
        }
    }

    private void drawBoard(GraphicsContext gc)
    {
        for (int y = 0; y < Game.BOARD_SIZE; y++)
        {
            for (int x = 0; x < Game.BOARD_SIZE; x++)
            {
                if ((y % 2 == 0 && x % 2 == 1) || (y % 2 == 1 && x % 2 == 0))
                {
                    gc.setFill(Color.DARKOLIVEGREEN);
                    gc.fillRect(x * GRID_CELL_SIZE, y * GRID_CELL_SIZE, GRID_CELL_SIZE, GRID_CELL_SIZE);
                } else
                {
                    gc.setFill(Color.WHITE);
                    gc.fillRect(x * GRID_CELL_SIZE, y * GRID_CELL_SIZE, GRID_CELL_SIZE, GRID_CELL_SIZE);
                }
            }
        }
    }

    private void drawPieces(GraphicsContext gc)
    {
        Piece[][] board = Game.getGame().getBoard();

        for (int y = 0; y < Game.BOARD_SIZE; y++)
        {
            for (int x = 0; x < Game.BOARD_SIZE; x++)
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
