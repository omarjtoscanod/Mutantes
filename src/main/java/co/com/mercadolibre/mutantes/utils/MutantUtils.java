package co.com.mercadolibre.mutantes.utils;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.com.mercadolibre.mutantes.exception.MutantException;

/**
 * Clase que contiene métodos que permiten realizar la búsqueda de
 * la secuencia ADN
 * @author omar
 *
 */
public final class MutantUtils {
	

	/** Guarda el número de secuencias mutantes encontradas */
	private static int contSecuencias = 0;
	
	/** Secuencia de ADN AAAA */
	private static final String CONST_AAAA = "AAAA";
	
	/** Secuencia de ADN CCCC */
    private static final String CONST_CCCC = "CCCC";
    
    /** Secuencia de ADN TTTT */
    private static final String CONST_TTTT = "TTTT";
    
    /** Secuencia de ADN GGGG */
    private static final String CONST_GGGG = "GGGG";
    
    /** Número de mínimo de secuencias de ADN que deben ser encontrados
     * para ser mutante */
    private static final int CONST_MINIMO_SECUENCIA_A_ENCONTRAR = 2;
    
    /** Tamaño de una secuencia adn válida */
    private static final int CONS_TAMANO_SECUENCIA_ADN_VALIDA = 4;
    
    /** Indica el tipo de sujeto humano */
    public static final String CONS_SUBJECT_HUMAN = "Humano";
    
    /** Indica el tipo de sujeto Mutante */
    public static final String CONS_SUBJECT_MUTANT = "Mutante";
    
    
	private MutantUtils() {
		super();
	}
	
	/**
	 * Busca la cadena de secuencia mutante en dirección
	 * horizontal por fila
	 * @param dnaSequence Arreglo con las secuencias ADN
	 * @return true si encuentra cadena ADN Mutante, 
	 * false en caso contrario
	 */
	private static boolean findHorizontal(char[][] dnaSequence) {
        boolean continuar = false;
        int dnaSequenceRange = dnaSequence.length;
        String secuence;
        for (int range = 0; range < dnaSequenceRange && !continuar; range++) {
            secuence = String.valueOf(dnaSequence[range]);
            continuar = findSequence(secuence);
        }
        return continuar;
    }
	
	/**
	 * Busca la cadena de secuencia mutante en dirección
	 * vertical por columna
	 * @param dnaSequence Arreglo con las secuencias ADN
	 * @return true si encuentra cadena ADN Mutante, 
	 * false en caso contrario
	 */
	private static boolean findVertical(char[][] dnaSequence){
        int dnaSequenceRange = dnaSequence.length;
        boolean continuar = false;
        StringBuilder secuence = new StringBuilder("");
        for (int range = 0; range < dnaSequenceRange && !continuar; range++) {
            for(int j = 0; j < dnaSequenceRange; j++) {
                secuence.append(String.valueOf(dnaSequence[j][range]));
            }
            continuar = findSequence(secuence.toString());
            secuence = new StringBuilder("");
        }

        return continuar;
    }
	
	/**
	 * Busca la cadena de secuencia mutante en dirección
	 * diagonal empezando por el primer registro del arreglo
	 * @param dnaSequence Arreglo con las secuencias ADN
	 * @return true si encuentra cadena ADN Mutante, 
	 * false en caso contrario
	 */
	private static boolean findDiagonalPrincipal(char[][] dnaSequence){
        int dnaSequenceRange = dnaSequence.length;
        StringBuilder secuence = new StringBuilder("");
        boolean continuar = false;
        for(int i = 0; i < dnaSequenceRange && !continuar; i++){
        	secuence.append(String.valueOf(dnaSequence[i][i]));
        }
        continuar = findSequence(secuence.toString());
        return continuar;
    }
	
	/**
	 * Busca la cadena de secuencia mutante en dirección
	 * diagonal empezando por el primer registro que se
	 * encuentra al lado izquierdo de la diagonal principal
	 * @param dnaSequence Arreglo con las secuencias ADN
	 * @return true si encuentra cadena ADN Mutante, 
	 * false en caso contrario
	 */
	private static boolean findDiagonalDerechaIzquierda(char[][] dnaSequence){
        int dnaSequenceRange = dnaSequence.length - 1;
        int sequenceLength = CONS_TAMANO_SECUENCIA_ADN_VALIDA;
        int index = 1;
        boolean continuarDerecha = false;
        boolean continuarIzquierda = false;
        StringBuilder sequenceDerecha = new StringBuilder("");
        StringBuilder sequenceIzquierda = new StringBuilder("");
        while(sequenceLength > 3 && !continuarDerecha && !continuarIzquierda){
            for(int i = 0; i < dnaSequenceRange; i++) {
                sequenceDerecha.append(dnaSequence[i][i + index]);
                sequenceIzquierda.append(dnaSequence[i + index][i]);
            }
            continuarDerecha = findSequence(sequenceDerecha.toString());
            continuarIzquierda = findSequence(sequenceIzquierda.toString());
            sequenceLength = sequenceDerecha.length();
            sequenceDerecha = new StringBuilder("");
            sequenceIzquierda = new StringBuilder("");
            index += 1;
            dnaSequenceRange -= 1;
        }
        if(continuarDerecha || continuarIzquierda){
            return true;
        }
        return false;
    }
	
	private static boolean findSequence(String secuence){
        if (contSecuencias < CONST_MINIMO_SECUENCIA_A_ENCONTRAR) {
            if (secuence.indexOf(CONST_CCCC) >= 0 || secuence.indexOf(CONST_TTTT) >= 0
                    || secuence.indexOf(CONST_AAAA) >= 0 || secuence.indexOf(CONST_GGGG) >= 0) {
                contSecuencias++;
                if(contSecuencias > 1){
                    return true;
                }
            }
        }
        return false;
    }
	
	/**
	 * Realiza la búsqueda de las secuencias de ADN en en adn
	 * ingresado
	 * @param dnaSequence Arreglo con las secuencias ADN
	 * @return true si encuentra cadena ADN Mutante, 
	 * false en caso contrario
	 */
	public static boolean analizarSecuenciaADN(char[][] dnaSequence){
        if(findHorizontal(dnaSequence))
            return true;
        if(findVertical(dnaSequence))
            return true;
        if(findDiagonalPrincipal(dnaSequence))
            return true;
        return findDiagonalDerechaIzquierda(dnaSequence);
    }
	
	/**
	 * Realiza validaciones previas antes de empezar la búsqueda del adn,
	 * las validaciones que realiza es de longitud, caractéres aceptados
	 * @param dna
	 * @return
	 * @throws MutantException
	 */
	public static char[][] validarADNIngresado (String[] dna) throws MutantException{
		int dnaLength = dna.length;
		char[][] dnaSequence = new char[dnaLength][dnaLength];
		Pattern pattern = Pattern.compile("[acgt]+", Pattern.CASE_INSENSITIVE);
		
		for (int index = 0; index < dnaLength; index++) {
			if (dna[index].length() != dnaLength) {
				throw new MutantException("El largo de la secuencia de ADN debe ser igual al numero de secuencias");
			}else if (!pattern.matcher(dna[index]).matches()) {
				throw new MutantException("El ADN humano y mutante contiene unicamente los caracteres A, C, G y T.");
			}else {
                dnaSequence[index] = dna[index].toUpperCase().toCharArray();
            }
		}
		if (dnaSequence.length < CONS_TAMANO_SECUENCIA_ADN_VALIDA) {
			throw new MutantException("El largo de la secuencia de ADN debe ser igual o mayor a" 
					+ CONS_TAMANO_SECUENCIA_ADN_VALIDA);
        }
		
		return dnaSequence;
	}
	

}
