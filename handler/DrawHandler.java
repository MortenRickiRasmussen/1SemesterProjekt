package handler;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Billet;
import model.Forestilling;
import model.Sal;

/**
 *
 * @author Jakob Ferdinandsen
 */
public class DrawHandler {

    private Graphics g;
    private int boxSize;
    private int screenOffset;
    private int walkwayOffset;
    private boolean[][] takenSeats;
    private Point seatsPoint;
    private Sal sal;
    private DBHandler db;
    private Forestilling forestilling;
    private int seatsY;
    private ArrayList<Integer> seatsArray;
    private int widthOffset;
    private int numberOfSeats;

    public DrawHandler() throws IOException, ClassNotFoundException, SQLException {
        boxSize = 20;
        screenOffset = 70;
        walkwayOffset = boxSize;
        seatsPoint = new Point(walkwayOffset, screenOffset);
        db = new DBHandler();
        seatsArray = new ArrayList<>();
    }

    public void eraseDrawing() {
        g.setColor(new Color(214, 217, 223));
        g.fillRect(0, 0, 1000, 1000);
        g.setColor(Color.BLACK);
    }

    public void drawGrid(int rows, int seats) {
        g.setColor(Color.WHITE);
        g.fillRect(widthOffset, 0, seats * boxSize + 2 * walkwayOffset + widthOffset, rows * boxSize + screenOffset + walkwayOffset);
        g.setColor(Color.BLACK);
        drawCanvas(seats);
        int newRow = screenOffset;
        int newSeat = walkwayOffset;
        for (int i = 0; i <= rows; i++) {
            g.drawLine(walkwayOffset+widthOffset, newRow, seats * boxSize + walkwayOffset+widthOffset, newRow);
            newRow += boxSize;
        }
        for (int i = 0; i <= seats; i++) {
            g.drawLine(newSeat+widthOffset, screenOffset, newSeat+widthOffset, screenOffset + rows * boxSize);
            newSeat += boxSize;
        }
    }

    public void drawCanvas(int seats) {
        g.setColor(Color.BLUE);
        g.fillRect(walkwayOffset+widthOffset, boxSize / 2 - 2, seats * boxSize, 4);
        g.setColor(Color.BLACK);
    }

    public void drawTakenSeat(int row, int seat) {
        int startX = (seat - 1) * boxSize + 1 + walkwayOffset + widthOffset;
        int startY = (row - 1) * boxSize + 1 + screenOffset;
        int endX = boxSize - 1;
        int endY = boxSize - 1;
        g.setColor(Color.RED);
        g.fillRect(startX, startY, endX, endY);
        g.setColor(Color.BLACK);
    }

    public void drawSelectedSeat(int row, int seat) {
        int startX = (seat - 1) * boxSize + 1 + walkwayOffset + widthOffset;
        int startY = (row - 1) * boxSize + 1 + screenOffset;
        int endX = boxSize - 1;
        int endY = boxSize - 1;
        g.setColor(Color.GREEN);
        g.fillRect(startX, startY, endX, endY);
        g.setColor(Color.BLACK);
    }

    public void drawForestillingsSal(Forestilling forestilling, Sal sal, ArrayList<Billet> billetter, Graphics g, int numberOfSeats, int panelWidth) {
        this.g = g;
        this.sal = sal;
        this.forestilling = forestilling;
        this.numberOfSeats = numberOfSeats;
        widthOffset = panelWidth-2*walkwayOffset-sal.getSædder()*boxSize;
        eraseDrawing();
        takenSeats = new boolean[sal.getRækker()][sal.getSædder()];
        for (int i = 0; i < sal.getRækker(); i++) {
            for (int j = 0; j < sal.getSædder(); j++) {
                takenSeats[i][j] = false;
            }
        }

        drawGrid(sal.getRækker(), sal.getSædder());
        for (int i = 0; i < billetter.size(); i++) {
            if (billetter.get(i).getForestillings_id() == forestilling.getId()) {
                drawTakenSeat(billetter.get(i).getRække(), billetter.get(i).getSædde());
                takenSeats[billetter.get(i).getRække() - 1][billetter.get(i).getSædde() - 1] = true;
            }
        }
        if (numberOfSeats != 0) {
            drawSeatsAtMouse(seatsPoint, numberOfSeats);
        }
    }

    public boolean isFree(int row, int startSeat, int numberOfSeats) {
        int start = startSeat;
        int freeCount = 0;
        for (int i = 0; i < numberOfSeats; i++) {
            if (takenSeats[row - 1][start - 1] == false) {
                freeCount++;
            }
            start++;
        }
        if (freeCount == numberOfSeats) {
            return true;
        } else {
            return false;
        }
    }

    public void setSeatsPoint(Point p) {
        seatsPoint = p;
    }
    

    public void drawSeatsAtMouse(Point p, int numberOfSeats) {
        int seatsX = p.x - walkwayOffset - widthOffset;
        seatsY = p.y - screenOffset;
        if (seatsX / 10 % 2 != 0) {
            seatsX = (seatsX / 10 - 1) / 2 + 1;
        } else {
            seatsX = (seatsX / 10) / 2 + 1;
        }
        if (seatsY / 10 % 2 != 0) {
            seatsY = (seatsY / 10 - 1) / 2 + 1;
        } else {
            seatsY = (seatsY / 10) / 2 + 1;
        }

        int seatsChosen = seatsX;
        for (int i = 1; i < numberOfSeats; i++) {
            seatsChosen++;
        }
        seatsArray.clear();
        if (seatsX > 0 && seatsChosen <= sal.getSædder() && seatsChosen > 0 && seatsY <= sal.getRækker() && seatsChosen > 0) {
            if (isFree(seatsY, seatsX, numberOfSeats) == true) {
                for (int i = 0; i < numberOfSeats; i++) {
                    drawSelectedSeat(seatsY, seatsX);
                    seatsArray.add(seatsX);
                    seatsX++;
                }
            }
        }

    }

    public boolean bestilBilletter(int telefonNummer) throws SQLException, ClassNotFoundException {
        if (!seatsArray.isEmpty()) {
            for (int i = 0; i < numberOfSeats; i++) {
                db.addBillet(forestilling.getId(), seatsY, seatsArray.get(i), telefonNummer);
            }
            return true;
        }else{
            return false;
        }
    }
}
