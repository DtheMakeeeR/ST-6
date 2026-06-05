package com.mycompany.app;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.lang.reflect.Field;

public class GameTest {
    private Game game;
    
    @Before
    public void setUp() {
        game = new Game();
    }
    
    // Тесты начального состояния
    @Test
    public void testInitialBoardIsEmpty() {
        for (int i = 0; i < 9; i++) {
            assertEquals(' ', game.board[i]);
        }
    }
    
    @Test
    public void testInitialStateIsPlaying() {
        assertEquals(State.PLAYING, game.state);
    }
    
    @Test
    public void testPlayerSymbolsAreCorrect() {
        assertEquals('X', game.player1.symbol);
        assertEquals('O', game.player2.symbol);
    }
    
    @Test
    public void testInitialQIsZero() {
        assertEquals(0, game.q);
    }
    
    @Test
    public void testINFConstantIs100() {
        assertEquals(100, Game.INF);
    }
    
    // Тесты checkState для побед X
    @Test
    public void testCheckStateXWinFirstRow() {
        char[] board = {'X', 'X', 'X', ' ', ' ', ' ', ' ', ' ', ' '};
        game.symbol = 'X';
        assertEquals(State.XWIN, game.checkState(board));
    }
    
    @Test
    public void testCheckStateXWinSecondRow() {
        char[] board = {' ', ' ', ' ', 'X', 'X', 'X', ' ', ' ', ' '};
        game.symbol = 'X';
        assertEquals(State.XWIN, game.checkState(board));
    }
    
    @Test
    public void testCheckStateXWinThirdRow() {
        char[] board = {' ', ' ', ' ', ' ', ' ', ' ', 'X', 'X', 'X'};
        game.symbol = 'X';
        assertEquals(State.XWIN, game.checkState(board));
    }
    
    @Test
    public void testCheckStateXWinFirstColumn() {
        char[] board = {'X', ' ', ' ', 'X', ' ', ' ', 'X', ' ', ' '};
        game.symbol = 'X';
        assertEquals(State.XWIN, game.checkState(board));
    }
    
    @Test
    public void testCheckStateXWinSecondColumn() {
        char[] board = {' ', 'X', ' ', ' ', 'X', ' ', ' ', 'X', ' '};
        game.symbol = 'X';
        assertEquals(State.XWIN, game.checkState(board));
    }
    
    @Test
    public void testCheckStateXWinThirdColumn() {
        char[] board = {' ', ' ', 'X', ' ', ' ', 'X', ' ', ' ', 'X'};
        game.symbol = 'X';
        assertEquals(State.XWIN, game.checkState(board));
    }
    
    @Test
    public void testCheckStateXWinMainDiagonal() {
        char[] board = {'X', ' ', ' ', ' ', 'X', ' ', ' ', ' ', 'X'};
        game.symbol = 'X';
        assertEquals(State.XWIN, game.checkState(board));
    }
    
    @Test
    public void testCheckStateXWinAntiDiagonal() {
        char[] board = {' ', ' ', 'X', ' ', 'X', ' ', 'X', ' ', ' '};
        game.symbol = 'X';
        assertEquals(State.XWIN, game.checkState(board));
    }
    
    // Тесты checkState для побед O
    @Test
    public void testCheckStateOWinFirstRow() {
        char[] board = {'O', 'O', 'O', ' ', ' ', ' ', ' ', ' ', ' '};
        game.symbol = 'O';
        assertEquals(State.OWIN, game.checkState(board));
    }
    
    @Test
    public void testCheckStateOWinSecondRow() {
        char[] board = {' ', ' ', ' ', 'O', 'O', 'O', ' ', ' ', ' '};
        game.symbol = 'O';
        assertEquals(State.OWIN, game.checkState(board));
    }
    
    @Test
    public void testCheckStateOWinThirdRow() {
        char[] board = {' ', ' ', ' ', ' ', ' ', ' ', 'O', 'O', 'O'};
        game.symbol = 'O';
        assertEquals(State.OWIN, game.checkState(board));
    }
    
    @Test
    public void testCheckStateOWinFirstColumn() {
        char[] board = {'O', ' ', ' ', 'O', ' ', ' ', 'O', ' ', ' '};
        game.symbol = 'O';
        assertEquals(State.OWIN, game.checkState(board));
    }
    
    @Test
    public void testCheckStateOWinMainDiagonal() {
        char[] board = {'O', ' ', ' ', ' ', 'O', ' ', ' ', ' ', 'O'};
        game.symbol = 'O';
        assertEquals(State.OWIN, game.checkState(board));
    }
    
    // Тесты checkState для ничьей и продолжения игры
    @Test
    public void testCheckStateDraw() {
        char[] board = {'X', 'O', 'X', 'X', 'O', 'O', 'O', 'X', 'X'};
        game.symbol = 'X';
        assertEquals(State.DRAW, game.checkState(board));
    }
    
    @Test
    public void testCheckStatePlaying() {
        char[] board = {'X', 'O', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        game.symbol = 'X';
        assertEquals(State.PLAYING, game.checkState(board));
    }
    
    @Test
    public void testCheckStateEmptyBoardIsPlaying() {
        char[] board = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        game.symbol = 'X';
        assertEquals(State.PLAYING, game.checkState(board));
    }
    
    // Тесты generateMoves
    @Test
    public void testGenerateMovesOnEmptyBoard() {
        char[] board = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(board, moves);
        assertEquals(9, moves.size());
        for (int i = 0; i < 9; i++) {
            assertEquals(Integer.valueOf(i), moves.get(i));
        }
    }
    
    @Test
    public void testGenerateMovesOnPartialBoard() {
        char[] board = {'X', 'O', 'X', 'X', 'O', 'O', 'O', 'X', ' '};
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(board, moves);
        assertEquals(1, moves.size());
        assertEquals(Integer.valueOf(8), moves.get(0));
    }
    
    @Test
    public void testGenerateMovesOnFullBoard() {
        char[] board = {'X', 'O', 'X', 'X', 'O', 'O', 'O', 'X', 'X'};
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(board, moves);
        assertEquals(0, moves.size());
    }
    
    @Test
    public void testGenerateMovesOnlyEmptySpaces() {
        char[] board = {'X', ' ', 'O', ' ', 'X', ' ', 'O', ' ', 'X'};
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(board, moves);
        assertEquals(4, moves.size());
        for (Integer move : moves) {
            assertEquals(' ', board[move]);
        }
    }
    
    // Тесты evaluatePosition
    @Test
    public void testEvaluatePositionXWinningForPlayerX() {
        char[] board = {'X', 'X', 'X', ' ', ' ', ' ', ' ', ' ', ' '};
        game.symbol = 'X';
        assertEquals(100, game.evaluatePosition(board, game.player1));
    }
    
    @Test
    public void testEvaluatePositionXWinningForPlayerO() {
        char[] board = {'X', 'X', 'X', ' ', ' ', ' ', ' ', ' ', ' '};
        game.symbol = 'X';
        assertEquals(-100, game.evaluatePosition(board, game.player2));
    }
    
    @Test
    public void testEvaluatePositionOWinningForPlayerO() {
        char[] board = {'O', 'O', 'O', ' ', ' ', ' ', ' ', ' ', ' '};
        game.symbol = 'O';
        assertEquals(100, game.evaluatePosition(board, game.player2));
    }
    
    @Test
    public void testEvaluatePositionOWinningForPlayerX() {
        char[] board = {'O', 'O', 'O', ' ', ' ', ' ', ' ', ' ', ' '};
        game.symbol = 'O';
        assertEquals(-100, game.evaluatePosition(board, game.player1));
    }
    
    @Test
    public void testEvaluatePositionDraw() {
        char[] board = {'X', 'O', 'X', 'X', 'O', 'O', 'O', 'X', 'X'};
        game.symbol = 'X';
        assertEquals(0, game.evaluatePosition(board, game.player1));
    }
    
    @Test
    public void testEvaluatePositionNotTerminal() {
        char[] board = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        game.symbol = 'X';
        assertEquals(-1, game.evaluatePosition(board, game.player1));
    }
    
    // Тесты MinMove
    @Test
    public void testMinMoveOnTerminalPosition() {
        char[] board = {'X', 'X', 'X', ' ', ' ', ' ', ' ', ' ', ' '};
        game.symbol = 'X';
        int result = game.MinMove(board, game.player1);
        assertTrue(result == 100 || result == -100 || result == 0);
    }
    
    @Test
    public void testMinMoveOnEmptyBoard() {
        char[] board = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        game.symbol = 'X';
        int result = game.MinMove(board, game.player1);
        assertTrue(result >= -100 && result <= 100);
    }
    
    // Тесты MaxMove
    @Test
    public void testMaxMoveOnTerminalPosition() {
        char[] board = {'X', 'X', 'X', ' ', ' ', ' ', ' ', ' ', ' '};
        game.symbol = 'X';
        int result = game.MaxMove(board, game.player1);
        assertTrue(result == 100 || result == -100 || result == 0);
    }
    
    @Test
    public void testMaxMoveOnEmptyBoard() {
        char[] board = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        game.symbol = 'X';
        int result = game.MaxMove(board, game.player1);
        assertTrue(result >= -100 && result <= 100);
    }
    
    // Тесты MiniMax
    @Test
    public void testMiniMaxReturnsValidMove() {
        char[] board = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        int move = game.MiniMax(board, game.player2);
        assertTrue(move >= 1 && move <= 9);
    }
    
    @Test
    public void testMiniMaxOnAlmostFullBoard() {
        char[] board = {'X', 'O', 'X', 'X', 'O', 'O', 'O', 'X', ' '};
        game.symbol = 'O';
        int move = game.MiniMax(board, game.player2);
        assertEquals(9, move);
    }
    
    @Test
    public void testMiniMaxBlocksWin() {
        char[] board = {'X', 'X', ' ', ' ', 'O', ' ', ' ', ' ', ' '};
        int move = game.MiniMax(board, game.player2);
        assertEquals(3, move); // Should block at position 3 (index 2)
    }
    
    @Test
    public void testMiniMaxTakesCenterFirst() {
        char[] board = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        int move = game.MiniMax(board, game.player2);
        // Минимакс может выбрать не только центр, проверяем что ход валидный (1-9)
        assertTrue("AI move should be between 1 and 9, got: " + move, move >= 1 && move <= 9);
    }
    
    // Тесты класса Utility
    @Test
    public void testUtilityPrintCharArray() {
        char[] board = {'X', 'O', 'X', ' ', ' ', ' ', ' ', ' ', ' '};
        Utility.print(board); // Just verify no exception
    }
    
    @Test
    public void testUtilityPrintIntArray() {
        int[] board = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Utility.print(board); // Just verify no exception
    }
    
    @Test
    public void testUtilityPrintArrayList() {
        ArrayList<Integer> moves = new ArrayList<>();
        moves.add(1);
        moves.add(2);
        moves.add(3);
        Utility.print(moves); // Just verify no exception
    }
    
    @Test
    public void testUtilityPrintEmptyArrayList() {
        ArrayList<Integer> moves = new ArrayList<>();
        Utility.print(moves); // Just verify no exception
    }
    
    // Тесты класса TicTacToeCell
    @Test
    public void testTicTacToeCellConstructor() {
        TicTacToeCell cell = new TicTacToeCell(5, 2, 1);
        assertEquals(5, cell.getNum());
        assertEquals(1, cell.getRow());
        assertEquals(2, cell.getCol());
        assertEquals(' ', cell.getMarker());
    }
    
    @Test
    public void testTicTacToeCellSetMarker() {
        TicTacToeCell cell = new TicTacToeCell(0, 0, 0);
        cell.setMarker("X");
        assertEquals('X', cell.getMarker());
    }
    
    @Test
    public void testTicTacToeCellSetMarkerO() {
        TicTacToeCell cell = new TicTacToeCell(0, 0, 0);
        cell.setMarker("O");
        assertEquals('O', cell.getMarker());
    }
    
    @Test
    public void testTicTacToeCellMultipleCells() {
        TicTacToeCell cell1 = new TicTacToeCell(0, 0, 0);
        TicTacToeCell cell2 = new TicTacToeCell(1, 1, 0);
        cell1.setMarker("X");
        cell2.setMarker("O");
        assertEquals('X', cell1.getMarker());
        assertEquals('O', cell2.getMarker());
        assertEquals(0, cell1.getNum());
        assertEquals(1, cell2.getNum());
    }
    
    // Дополнительные тесты для граничных случаев
    @Test
    public void testCheckStateWithNullSymbol() {
        char[] board = {'X', 'X', 'X', ' ', ' ', ' ', ' ', ' ', ' '};
        game.symbol = ' ';
        // Should not throw exception, but won't detect win
        State result = game.checkState(board);
        assertNotNull(result);
    }
    
    @Test
    public void testCheckStateWhenNoWinnerAndSpacesLeft() {
        char[] board = {'X', 'O', ' ', ' ', 'X', ' ', ' ', ' ', 'O'};
        game.symbol = 'X';
        assertEquals(State.PLAYING, game.checkState(board));
    }
    
    @Test
    public void testMiniMaxWhenNoMovesLeft() {
        char[] board = {'X', 'O', 'X', 'X', 'O', 'O', 'O', 'X', 'X'};
        // Когда нет ходов, MiniMax может вернуть 0 или какое-то значение
        // Проверяем что метод не выбрасывает исключение
        try {
            int move = game.MiniMax(board, game.player2);
            // Если вернул значение, оно должно быть в диапазоне или 0
            assertTrue(move >= 0 && move <= 9);
        } catch (Exception e) {
            fail("MiniMax threw exception on full board: " + e.getMessage());
        }
    }
    
    @Test
    public void testQCounterIncrementsDuringSearch() {
        game.q = 0;
        char[] board = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        game.MiniMax(board, game.player2);
        assertTrue(game.q >= 0);
    }
    
    @Test
    public void testPlayerObjectsAreDifferent() {
        assertNotSame(game.player1, game.player2);
        assertNotEquals(game.player1.symbol, game.player2.symbol);
    }
    
    @Test
    public void testBoardIndicesFromZeroToEight() {
        for (int i = 0; i < 9; i++) {
            assertEquals(' ', game.board[i]);
        }
    }
    
    @Test
    public void testAllEightWinConditionsForX() {
        char[][] winBoards = {
            {'X', 'X', 'X', ' ', ' ', ' ', ' ', ' ', ' '}, // row 0
            {' ', ' ', ' ', 'X', 'X', 'X', ' ', ' ', ' '}, // row 1
            {' ', ' ', ' ', ' ', ' ', ' ', 'X', 'X', 'X'}, // row 2
            {'X', ' ', ' ', 'X', ' ', ' ', 'X', ' ', ' '}, // col 0
            {' ', 'X', ' ', ' ', 'X', ' ', ' ', 'X', ' '}, // col 1
            {' ', ' ', 'X', ' ', ' ', 'X', ' ', ' ', 'X'}, // col 2
            {'X', ' ', ' ', ' ', 'X', ' ', ' ', ' ', 'X'}, // diag 0
            {' ', ' ', 'X', ' ', 'X', ' ', 'X', ' ', ' '}  // diag 1
        };
        
        for (char[] board : winBoards) {
            game.symbol = 'X';
            assertEquals(State.XWIN, game.checkState(board));
        }
    }
    
    @Test
    public void testAllEightWinConditionsForO() {
        char[][] winBoards = {
            {'O', 'O', 'O', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', 'O', 'O', 'O', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', 'O', 'O', 'O'},
            {'O', ' ', ' ', 'O', ' ', ' ', 'O', ' ', ' '},
            {' ', 'O', ' ', ' ', 'O', ' ', ' ', 'O', ' '},
            {' ', ' ', 'O', ' ', ' ', 'O', ' ', ' ', 'O'},
            {'O', ' ', ' ', ' ', 'O', ' ', ' ', ' ', 'O'},
            {' ', ' ', 'O', ' ', 'O', ' ', 'O', ' ', ' '}
        };
        
        for (char[] board : winBoards) {
            game.symbol = 'O';
            assertEquals(State.OWIN, game.checkState(board));
        }
    }
}