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

    private void highlightVectors(VectorPiece p, GraphicsContext gc)
    {
        MoveVector[] moves = Game.getGame().getPossibleVectorMoves(p);

        for (int i = 0; i < moves.length; i++)
        {
            if (moves[i] != null)
            {
                Coordinate highlightCoordinates = moves[i].applyVector(p.getLocation());
                int highlightX = highlightCoordinates.getX() * GRID_CELL_SIZE + (GRID_CELL_SIZE/4);
                int highlightY = highlightCoordinates.getY() * GRID_CELL_SIZE + (GRID_CELL_SIZE/4);

                gc.fillOval(highlightX, highlightY, GRID_CELL_SIZE/2, GRID_CELL_SIZE/2);
            }
        }
    }

    private void highlightDirectional(DirectionPiece p, GraphicsContext gc)
    {
        MoveDirection[] moves = Game.getGame().getPossibleDirectionalMoves(p);
        System.out.println(moves.length);
        for (int i = 0; i < moves.length; i++)
        {
            if (moves[i] != null)
            {
                MoveDirection move = moves[i];

                switch (move.getDirection())
                {
                    case Direction.NORTH:
                        for (int j = 0; j < move.getDistance(); j++)
                        {
                            int highlightX = p.getX() * GRID_CELL_SIZE + (GRID_CELL_SIZE/4);
                            int highlightY = (p.getY() - (j + 1))  * GRID_CELL_SIZE + (GRID_CELL_SIZE/4);
                            gc.fillOval(highlightX, highlightY, GRID_CELL_SIZE/2, GRID_CELL_SIZE/2);
                        }
                        break;

                    case Direction.SOUTH:
                        for (int j = 0; j < move.getDistance(); j++)
                        {
                            int highlightX = p.getX() * GRID_CELL_SIZE + (GRID_CELL_SIZE/4);
                            int highlightY = (p.getY() + j + 1)  * GRID_CELL_SIZE + (GRID_CELL_SIZE/4);
                            gc.fillOval(highlightX, highlightY, GRID_CELL_SIZE/2, GRID_CELL_SIZE/2);
                        }
                        break;

                    case Direction.EAST:
                        for (int j = 0; j < move.getDistance(); j++)
                        {
                            int highlightX = (p.getX() + j + 1) * GRID_CELL_SIZE + (GRID_CELL_SIZE/4);
                            int highlightY = p.getY()  * GRID_CELL_SIZE + (GRID_CELL_SIZE/4);
                            gc.fillOval(highlightX, highlightY, GRID_CELL_SIZE/2, GRID_CELL_SIZE/2);
                        }
                        break;

                    case Direction.WEST:
                        for (int j = 0; j < move.getDistance(); j++)
                        {
                            int highlightX = (p.getX() - (j + 1)) * GRID_CELL_SIZE + (GRID_CELL_SIZE/4);
                            int highlightY = p.getY() * GRID_CELL_SIZE + (GRID_CELL_SIZE/4);
                            gc.fillOval(highlightX, highlightY, GRID_CELL_SIZE/2, GRID_CELL_SIZE/2);
                        }
                        break;

                    case Direction.NORTH_EAST:
                        for (int j = 0; j < move.getDistance(); j++)
                        {
                            int highlightX = (p.getX() + j + 1) * GRID_CELL_SIZE + (GRID_CELL_SIZE/4);
                            int highlightY = (p.getY() - (j + 1)) * GRID_CELL_SIZE + (GRID_CELL_SIZE/4);
                            gc.fillOval(highlightX, highlightY, GRID_CELL_SIZE/2, GRID_CELL_SIZE/2);
                        }
                        break;

                    case Direction.SOUTH_EAST:
                        for (int j = 0; j < move.getDistance(); j++)
                        {
                            int highlightX = (p.getX() + j + 1) * GRID_CELL_SIZE + (GRID_CELL_SIZE/4);
                            int highlightY = (p.getY() + j + 1) * GRID_CELL_SIZE + (GRID_CELL_SIZE/4);
                            gc.fillOval(highlightX, highlightY, GRID_CELL_SIZE/2, GRID_CELL_SIZE/2);
                        }
                        break;

                    case Direction.SOUTH_WEST:
                        for (int j = 0; j < move.getDistance(); j++)
                        {
                            int highlightX = (p.getX() - (j + 1)) * GRID_CELL_SIZE + (GRID_CELL_SIZE/4);
                            int highlightY = (p.getY() + j + 1) * GRID_CELL_SIZE + (GRID_CELL_SIZE/4);
                            gc.fillOval(highlightX, highlightY, GRID_CELL_SIZE/2, GRID_CELL_SIZE/2);
                        }
                        break;

                    case Direction.NORTH_WEST:
                        for (int j = 0; j < move.getDistance(); j++)
                        {
                            int highlightX = (p.getX() - (j + 1)) * GRID_CELL_SIZE + (GRID_CELL_SIZE/4);
                            int highlightY = (p.getY() - (j + 1)) * GRID_CELL_SIZE + (GRID_CELL_SIZE/4);
                            gc.fillOval(highlightX, highlightY, GRID_CELL_SIZE/2, GRID_CELL_SIZE/2);
                        }
                        break;

                }
            }
        }
    }

    private void drawHighlights(Piece p, GraphicsContext gc)
    {
        if (p != null)
        {
            gc.setFill(Color.STEELBLUE);
            if (p instanceof VectorPiece)
            {
                this.highlightVectors((VectorPiece) p, gc);
            } else
            {
                this.highlightDirectional((DirectionPiece) p, gc);
            }
        }
    }

    private void drawBoard(GraphicsContext gc)
    {
        for (int y = 0; y < Game.BOARD_SIZE; y++)
        {
            for (int x = 0; x < Game.BOARD_SIZE; x++)
            {
                if (isDarkTile(x, y))
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

    private boolean isDarkTile(int x, int y)
    {
        return this.isEvenOnOddRow(x, y) || this.isOddOnEvenRow(x, y);
    }

    private boolean isEvenOnOddRow(int x, int y)
    {
        return y % 2 == 1 && x % 2 == 0;
    }

    private boolean isOddOnEvenRow(int x, int y)
    {
        return y % 2 == 0 && x % 2 == 1;
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
