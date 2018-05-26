package es.codeurjc.ais.tictactoe;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.hamcrest.MockitoHamcrest.argThat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import es.codeurjc.ais.tictactoe.Connection;
import es.codeurjc.ais.tictactoe.Player;
import es.codeurjc.ais.tictactoe.TicTacToeGame;
import es.codeurjc.ais.tictactoe.TicTacToeGame.EventType;
import es.codeurjc.ais.tictactoe.TicTacToeGame.WinnerValue;

public class pruebasdobles {

	TicTacToeGame juego;
	Player p1, p2;
	Connection conexion1,conexion2;
	
	@Before
	public void setUp(){
		//Crear objeto TicTacToeGame
		juego = new TicTacToeGame();
		//Crear dobles de los objetos Connection
		conexion1 = mock(Connection.class);
		conexion2 = mock(Connection.class);
		//Añadir los dobles al objeto TicTacToeGame
		juego.addConnection(conexion1);
		juego.addConnection(conexion2);
		//Crear los dos jugadores
		p1 = new Player(1,"x", "Plam");
		p2 = new Player(2,"o", "Cris");
		//Añadir jugador 1
		juego.addPlayer(p1);
		//Comprobar que la conexion 1 ha recibido el evento JOIN_GAME con el jugador 1
		verify(conexion1).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(p1)));
		//Comprobar que la conexion 2 ha recibido el evento JOIN_GAME con el jugador 1
		verify(conexion2).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(p1)));
		//Borrar registro de llamadas
		reset(conexion1);
		reset(conexion2);
		//Añadir jugador 2
		juego.addPlayer(p2);
		//Comprobar que la conexion 1 ha recibido el evento JOIN_GAME con ambos jugadores
		verify(conexion1).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(p1, p2)));
		//Comprobar que la conexion 2 ha recibido el evento JOIN_GAME con ambos jugadores
		verify(conexion2).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(p1, p2)));
		//Pasar el turno al jugador 1
		verify(conexion1).sendEvent(eq(EventType.SET_TURN), eq(p1));
		verify(conexion2).sendEvent(eq(EventType.SET_TURN), eq(p1));
	}
	
	@Test
	public void testPlayer1Winner() {
		//Jugador 1 marca la casilla 0
		juego.mark(0);
		//Pasa el turno al jugador 2
		verify(conexion1).sendEvent(eq(EventType.SET_TURN), eq(p2));
		verify(conexion2).sendEvent(eq(EventType.SET_TURN), eq(p2));
		//Borrar registro de llamadas
		reset(conexion1);
		reset(conexion2);
		//Jugador 2 marca la casilla 2
		juego.mark(2);
		//Pasa el turno al jugador 1
		verify(conexion1).sendEvent(eq(EventType.SET_TURN), eq(p1));
		verify(conexion2).sendEvent(eq(EventType.SET_TURN), eq(p1));
		//Borrar registro de llamadas
		reset(conexion1);
		reset(conexion2);
		//Jugador 1 marca la casilla 4
		juego.mark(4);
		//Pasa el turno al jugador 2
		verify(conexion1).sendEvent(eq(EventType.SET_TURN), eq(p2));
		verify(conexion2).sendEvent(eq(EventType.SET_TURN), eq(p2));
		//Borrar registro de llamadas
		reset(conexion1);
		reset(conexion2);
		//Jugador 2 marca la casilla 5
		juego.mark(5);
		//Pasa el turno al jugador 1
		verify(conexion1).sendEvent(eq(EventType.SET_TURN), eq(p1));
		verify(conexion2).sendEvent(eq(EventType.SET_TURN), eq(p1));
		//Borrar registro de llamadas
		reset(conexion1);
		reset(conexion2);
		//Jugador 1 marca la casilla 8
		juego.mark(8);
		
		ArgumentCaptor<WinnerValue> argumento = ArgumentCaptor.forClass(WinnerValue.class);
		verify(conexion1).sendEvent(eq(EventType.GAME_OVER), argumento.capture());
		WinnerValue evento = (WinnerValue) argumento.getValue();
		//Comprobamos que la conexion 2 recibe GAME_OVER
		verify(conexion2).sendEvent(eq(EventType.GAME_OVER), eq(evento));
		//Comprobamos que ha ganado el jugador 1
		assertThat(evento.player.equals(p1));
		//Comprobamos que no ha ganado el jugador 2
		assertThat(!evento.player.equals(p2));
		//Comprobamos que no hay empate
		assertNotNull(evento);
		
	}
	
	@Test
	public void testPlayer2Winner() {

		//Jugador 1 marca la casilla 3
		juego.mark(3);
		//Pasa el turno al jugador 2
		verify(conexion1).sendEvent(eq(EventType.SET_TURN), eq(p2));
		verify(conexion2).sendEvent(eq(EventType.SET_TURN), eq(p2));
		//Borrar registro de llamadas
		reset(conexion1);
		reset(conexion2);
		//Jugador 2 marca la casilla 0
		juego.mark(0);
		//Pasa el turno al jugador 1
		verify(conexion1).sendEvent(eq(EventType.SET_TURN), eq(p1));
		verify(conexion2).sendEvent(eq(EventType.SET_TURN), eq(p1));
		//Borrar registro de llamadas
		reset(conexion1);
		reset(conexion2);
		//Jugador 1 marca la casilla 5
		juego.mark(5);
		//Pasa el turno al jugador 2
		verify(conexion1).sendEvent(eq(EventType.SET_TURN), eq(p2));
		verify(conexion2).sendEvent(eq(EventType.SET_TURN), eq(p2));
		//Borrar registro de llamadas
		reset(conexion1);
		reset(conexion2);
		//Jugador 2 marca la casilla 1
		juego.mark(1);
		//Pasa el turno al jugador 1
		verify(conexion1).sendEvent(eq(EventType.SET_TURN), eq(p1));
		verify(conexion2).sendEvent(eq(EventType.SET_TURN), eq(p1));
		//Borrar registro de llamadas
		reset(conexion1);
		reset(conexion2);
		//Jugador 1 marca la casilla 8
		juego.mark(8);
		//Pasa el turno al jugador 2
		verify(conexion1).sendEvent(eq(EventType.SET_TURN), eq(p2));
		verify(conexion2).sendEvent(eq(EventType.SET_TURN), eq(p2));
		//Borrar registro de llamadas
		reset(conexion1);
		reset(conexion2);
		//Jugador 2 marca la casilla 2
		juego.mark(2);
		
		ArgumentCaptor<WinnerValue> argumento = ArgumentCaptor.forClass(WinnerValue.class);
		verify(conexion1).sendEvent(eq(EventType.GAME_OVER), argumento.capture());
		WinnerValue evento = (WinnerValue) argumento.getValue();
		//Comprobamos que la conexion 2 recibe GAME_OVER
		verify(conexion2).sendEvent(eq(EventType.GAME_OVER), eq(evento));
		//Comprobamos que ha ganado el jugador 2
		assertThat(evento.player.equals(p2));
		//Comprobamos que no ha ganado el jugador 1
		assertThat(!evento.player.equals(p1));
		//Comprobamos que no hay empate
		assertNotNull(evento);
		
	}
	
	@Test
	public void testDraw() {

		//Jugador 1 marca la casilla 0	
		juego.mark(0);
		//Pasa el turno al jugador 2
		verify(conexion1).sendEvent(eq(EventType.SET_TURN), eq(p2));
		verify(conexion2).sendEvent(eq(EventType.SET_TURN), eq(p2));
		//Borrar registro de llamadas
		reset(conexion1);
		reset(conexion2);
		//Jugador 2 marca la casilla 4
		juego.mark(4);
		//Pasa el turno al jugador 1
		verify(conexion1).sendEvent(eq(EventType.SET_TURN), eq(p1));
		verify(conexion2).sendEvent(eq(EventType.SET_TURN), eq(p1));
		//Borrar registro de llamadas
		reset(conexion1);
		reset(conexion2);
		//Jugador 1 marca la casilla 8
		juego.mark(8);
		//Pasa el turno al jugador 2
		verify(conexion1).sendEvent(eq(EventType.SET_TURN), eq(p2));
		verify(conexion2).sendEvent(eq(EventType.SET_TURN), eq(p2));
		//Borrar registro de llamadas
		reset(conexion1);
		reset(conexion2);
		//Jugador 2 marca la casilla 1
		juego.mark(1);
		//Pasa el turno al jugador 1
		verify(conexion1).sendEvent(eq(EventType.SET_TURN), eq(p1));
		verify(conexion2).sendEvent(eq(EventType.SET_TURN), eq(p1));
		//Borrar registro de llamadas
		reset(conexion1);
		reset(conexion2);
		//Jugador 1 marca la casilla 7
		juego.mark(7);
		//Pasa el turno al jugador 2
		verify(conexion1).sendEvent(eq(EventType.SET_TURN), eq(p2));
		verify(conexion2).sendEvent(eq(EventType.SET_TURN), eq(p2));
		//Borrar registro de llamadas
		reset(conexion1);
		reset(conexion2);
		//Jugador 2 marca la casilla 6
		juego.mark(6);
		//Pasa el turno al jugador 1
		verify(conexion1).sendEvent(eq(EventType.SET_TURN), eq(p1));
		verify(conexion2).sendEvent(eq(EventType.SET_TURN), eq(p1));
		//Borrar registro de llamadas
		reset(conexion1);
		reset(conexion2);
		//Jugador 1 marca la casilla 2
		juego.mark(2);
		//Pasa el turno al jugador 2
		verify(conexion1).sendEvent(eq(EventType.SET_TURN), eq(p2));
		verify(conexion2).sendEvent(eq(EventType.SET_TURN), eq(p2));
		//Borrar registro de llamadas
		reset(conexion1);
		reset(conexion2);
		//Jugador 2 marca la casilla 5
		juego.mark(5);
		//Pasa el turno al jugador 1
		verify(conexion1).sendEvent(eq(EventType.SET_TURN), eq(p1));
		verify(conexion2).sendEvent(eq(EventType.SET_TURN), eq(p1));
		//Borrar registro de llamadas
		reset(conexion1);
		reset(conexion2);
		//Jugador 1 marca la casilla 3
		juego.mark(3);
		ArgumentCaptor<WinnerValue> argumento = ArgumentCaptor.forClass(WinnerValue.class);
		//Comprobamos que la conexion 1 recibe GAME_OVER
		verify(conexion1).sendEvent(eq(EventType.GAME_OVER), argumento.capture());
		Object evento =  argumento.getValue();
		//Comprobamos que la conexion 2 recibe GAME_OVER
		verify(conexion2).sendEvent(eq(EventType.GAME_OVER), eq(evento));
		//Comprobamos empate
		assertNull(evento);
		
	}

}
