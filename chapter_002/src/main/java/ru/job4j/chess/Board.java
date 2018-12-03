package ru.job4j.chess;

import ru.job4j.chess.figures.*;
import ru.job4j.chess.exceptions.*;

import java.util.Arrays;
import java.util.Optional;

/**
 * Class Board Реализация шахматной доски.
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 12.01.2018
 */
class Board {
    Figure[][] figures = new Figure[8][8];

    /**
     * Метод заполняет шахматною доску фигурами согласно правилам шахмат.
     * На данном этапе на доску ставятся только слоны.
     */
    private void fillBoard() {
        figures[2][0] = new Bishop(new Cell(2, 0));
        figures[5][0] = new Bishop(new Cell(5, 0));
        figures[2][7] = new Bishop(new Cell(2, 7));
        figures[5][7] = new Bishop(new Cell(5, 7));
    }

    /**
     * Конструктор.
     */
    Board() {
        fillBoard();
    }

    /**
     * Метод реализует ход фигуры.
     * @return Успешность хода.
     * @param source текущая позиция фигуры
     * @param dest позиция фигуры после перемещения
     * @throws ImpossibleMoveException ImpossibleMoveException
     * @throws OccupiedWayException OccupiedWayException
     * @throws FigureNotFoundException FigureNotFoundException
     */
    boolean move(Cell source, Cell dest)
            throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {

        boolean result;
        Figure figure = figures[source.getX()][source.getY()];
        if (figure != null) {
            Cell[] cells = figure.way(source, dest);

            Optional<Cell> optionalCell = Arrays.stream(cells)
                    .filter(cell -> (figures[cell.getX()][cell.getY()] != null))
                    .findFirst();
            if (optionalCell.isPresent()) {
                throw new OccupiedWayException(
                        String.format("Ход невозможен: на пути находится фигура в ячейке \"%d %d\".",
                                optionalCell.get().getX(), optionalCell.get().getY()));
            }

            /*for (Cell cell : cells) {
                if (figures[cell.getX()][cell.getY()] != null) {
                    throw new OccupiedWayException(
                            String.format("Ход невозможен: на пути находится фигура в ячейке \"%d %d\".",
                                            cell.getX(), cell.getY()));
                }
            }*/

            figures[dest.getX()][dest.getY()] = figure.copy(dest);
            figures[source.getX()][source.getY()] = null;

            result = true;
        } else {
            throw new FigureNotFoundException(
                    String.format("Ячейка \"%d %d\" пустая.", source.getX(), source.getY()));
        }
        return result;
    }
}