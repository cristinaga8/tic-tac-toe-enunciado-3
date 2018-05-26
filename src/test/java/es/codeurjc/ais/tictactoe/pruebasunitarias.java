package es.codeurjc.ais.tictactoe;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import es.codeurjc.ais.tictactoe.Board;
import es.codeurjc.ais.tictactoe.Player;

class pruebasunitarias {
	
	//Creamos tablero
	 Board board = new Board();
	 //Inicializamos jugadores
	 Player p1= new Player(1,"x", "Plam");
	 Player p2= new Player(2,"o","Cris");

	@Test
	public void testPlayer1Winner() {
		 //Array con posicion ganadora
		 int[] pos= {0,4,8};
		 //Colocamos x en la celda 0 
		 board.getCell(0).value= p1.getLabel();
		 //Colocamos o en celda 2
		 board.getCell(2).value=p2.getLabel();
		 //Colocamos x en la celda 4
		 board.getCell(4).value=p1.getLabel();
		 //Colocamos o en la celda 5
		 board.getCell(5).value=p2.getLabel();
		 //Colocamos x en la celda 8
		 board.getCell(8).value=p1.getLabel();
		 //Comprobamos si el jugador 1 tiene la posicion ganadora
		 assertArrayEquals(pos,board.getCellsIfWinner(p1.getLabel()));
		 //Comprobamos que el jugador 2 no es ganador
		 assertNull(board.getCellsIfWinner(p2.getLabel()));
		 //Comprobams que no hay empate
		 assertEquals(false,board.checkDraw());
		 
		 
	}
	@Test
	public void testPlayer2Winner() {
		//Array con posicion ganadora
		 int[] pos= {0,1,2};
		 //Colocamos x en la posicion 3
		 board.getCell(3).value=p1.getLabel();	
		 //Colocamos o en la posicion 0
		 board.getCell(0).value= p2.getLabel();
		//Colocamos x en la posicion 5
		 board.getCell(5).value=p1.getLabel();
		 //Colocamos o en la posicion 1
		 board.getCell(1).value=p2.getLabel();
		//Colocamos x en la posicion 8
		 board.getCell(8).value=p1.getLabel();
		 //Colocamos o en la posicion 2
		 board.getCell(2).value=p2.getLabel();
		//Comprobamos si el jugador 2 tiene la posicon ganadora
		 assertArrayEquals(pos,board.getCellsIfWinner(p2.getLabel()));
		 //Comprobamos que el jugador 1 no gana
		 assertNull(board.getCellsIfWinner(p1.getLabel()));
		 //Comprobamos que no hay empate
		 assertEquals(false, board.checkDraw());
	}
	@Test
	public void testDraw() {
		 //Colocamos x en posicion 0
		 board.getCell(0).value=p1.getLabel();
		 //Colocamos o en posicion 5
		 board.getCell(5).value=p2.getLabel();
		 //Colocamos x en posicion 2
		 board.getCell(2).value=p1.getLabel();
		 //Colocamos o en posicion 4
		 board.getCell(4).value=p2.getLabel();
		 //Colocamos x en posicion 1
		 board.getCell(1).value=p1.getLabel();
		 //Colocamos o en posicion 8
		 board.getCell(8).value=p2.getLabel();
		 //Colocamos x en posicion 6
		 board.getCell(6).value=p1.getLabel();
		 //Colocamos o en posisicion 3
		 board.getCell(3).value=p2.getLabel();
		 //Colocamos x en posicion 7
		 board.getCell(7).value=p1.getLabel();
		//Comprobamos que hay empate
		assertTrue(board.checkDraw());
		 
	}
}
