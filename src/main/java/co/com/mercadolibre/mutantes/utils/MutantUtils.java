package co.com.mercadolibre.mutantes.utils;

import java.util.function.Predicate;
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
    private static final int CONST_MAYOR_QUE_SECUENCIA = 3;
    
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
		int dnaSequenceSize = dnaSequence.length;
        boolean isMutant = false;
        String sequence;
        for (int i = 0; i < dnaSequenceSize && !isMutant; i++) {
            sequence = String.valueOf(dnaSequence[i]);
            isMutant = findSequence(sequence);
        }
        return isMutant;
    }
	
	/**
	 * Busca la cadena de secuencia mutante en dirección
	 * vertical por columna
	 * @param dnaSequence Arreglo con las secuencias ADN
	 * @return true si encuentra cadena ADN Mutante, 
	 * false en caso contrario
	 */
	private static boolean findVertical(char[][] dnaSequence){
		int dnaSequenceSize = dnaSequence.length;
        boolean isMutant = false;
        StringBuilder sequence = new StringBuilder();
        for (int i = 0; i < dnaSequenceSize && !isMutant; i++) {
            for(int j = 0; j < dnaSequenceSize; j++) {
                sequence.append(dnaSequence[j][i]);
            }
            isMutant = findSequence(sequence.toString());
            sequence = new StringBuilder("");
        }

        return isMutant;
    }
	
	/**
	 * Busca la cadena de secuencia mutante en dirección
	 * diagonal  empezando por el primer registro del arreglo
	 * @param dnaSequence Arreglo con las secuencias ADN
	 * @return true si encuentra cadena ADN Mutante, 
	 * false en caso contrario
	 */
	private static boolean findDiagonalPrincipalLeft(char[][] dnaSequence){
        int dnaSequenceSize = dnaSequence.length;
        boolean isMutant = false;
        StringBuilder sequence = new StringBuilder();
        for(int i = 0; i < dnaSequenceSize && !isMutant; i++){
            sequence.append(dnaSequence[i][i]);
        }
        isMutant = findSequence(sequence.toString());
        return isMutant;
    }
	
	/**
	 * Busca la cadena de secuencia mutante en dirección
	 * diagonal empezando por el último registro del arreglo
	 * @param dnaSequence Arreglo con las secuencias ADN
	 * @return true si encuentra cadena ADN Mutante, 
	 * false en caso contrario
	 */
	private static boolean findDiagonalPrincipalRight(char[][] dnaSequence){
        int dnaSequenceSize = dnaSequence.length;
        boolean isMutant = false;
        StringBuilder sequence = new StringBuilder();
        int index = 0;
        for(int i = dnaSequenceSize - 1; i >= 0 && !isMutant; i--){
            sequence.append(dnaSequence[index][i]);
            index++;
        }
        isMutant = findSequence(sequence.toString());
        return isMutant;
    }
	
	/**
	 * Busca la cadena de secuencia mutante en las diagonales
	 * izquierdas desde el primer registro más uno
	 * @param dnaSequence Arreglo con las secuencias ADN
	 * @return true si encuentra cadena ADN Mutante, 
	 * false en caso contrario
	 */
	private static boolean findDiagonalsLeftInitial(char[][] dnaSequence){
        int dnaSequenceSize = dnaSequence.length - 1;
        int sequenceLength = CONS_TAMANO_SECUENCIA_ADN_VALIDA;
        int index = 1;
        boolean isMutant = false;
        StringBuilder sequence = new StringBuilder();
        while(sequenceLength > CONST_MAYOR_QUE_SECUENCIA && !isMutant){
            for(int i = 0; i < dnaSequenceSize; i++) {
                sequence.append(dnaSequence[i + index][i]);
            }
            isMutant = findSequence(sequence.toString());
            sequenceLength = sequence.length();
            sequence = new StringBuilder("");
            index += 1;
            dnaSequenceSize -= 1;
        }
        return isMutant;
    }
	
	/**
	 * Busca la cadena de secuencia mutante en las diagonales
	 * derechas desde el primer registro más uno
	 * @param dnaSequence Arreglo con las secuencias ADN
	 * @return true si encuentra cadena ADN Mutante, 
	 * false en caso contrario
	 */
	private static boolean findDiagonalsRightInitial(char[][] dnaSequence){
        int dnaSequenceSize = dnaSequence.length - 1;
        int sequenceLength = CONS_TAMANO_SECUENCIA_ADN_VALIDA;
        int index = 1;
        boolean isMutant = false;
        StringBuilder sequence = new StringBuilder();
        while(sequenceLength > CONST_MAYOR_QUE_SECUENCIA && !isMutant){
            for(int i = 0; i < dnaSequenceSize; i++) {
                sequence.append(dnaSequence[i][i + index]);
            }
            isMutant = findSequence(sequence.toString());
            sequenceLength = sequence.length();
            sequence = new StringBuilder("");
            index += 1;
            dnaSequenceSize -= 1;
        }
        return isMutant;
    }
	
	/**
	 * Busca la cadena de secuencia mutante en las diagonales
	 * izquierdas desde el último registro más uno
	 * @param dnaSequence Arreglo con las secuencias ADN
	 * @return true si encuentra cadena ADN Mutante, 
	 * false en caso contrario
	 */
	private static boolean findDiagonalsLeftFinal(char[][] dnaSequence){
        int dnaSequenceSize = dnaSequence.length;
        int sequenceLength = CONS_TAMANO_SECUENCIA_ADN_VALIDA;
        int index = 0;
        boolean isMutant = false;
        StringBuilder sequence = new StringBuilder();
        while(sequenceLength > CONST_MAYOR_QUE_SECUENCIA && !isMutant){
            for(int i = dnaSequenceSize - 1; i > 0 && sequenceLength > CONST_MAYOR_QUE_SECUENCIA && !isMutant; i--) {
                sequence.append(dnaSequence[index][i - 1]);
                index += 1;
            }
            isMutant = findSequence(sequence.toString());
            sequenceLength = sequence.length();
            sequence = new StringBuilder("");
            index = 0;
            dnaSequenceSize -= 1;
        }
        return isMutant;
    }
	
	/**
	 * Busca la cadena de secuencia mutante en las diagonales
	 * derechas desde el último registro más uno
	 * @param dnaSequence Arreglo con las secuencias ADN
	 * @return true si encuentra cadena ADN Mutante, 
	 * false en caso contrario
	 */
	private static boolean findDiagonalsRightFinal(char[][] dnaSequence){
        int dnaSequenceSize = dnaSequence.length - 1;
        int sequenceLength = CONS_TAMANO_SECUENCIA_ADN_VALIDA;
        int indexColumna = 6;
        int indexFila = 0;
        int valorInicialIndex = 0;
        boolean isMutant = false;
        StringBuilder sequence = new StringBuilder();
        while(sequenceLength > CONST_MAYOR_QUE_SECUENCIA && !isMutant){
            for(int i = valorInicialIndex; i < dnaSequenceSize && sequenceLength > CONST_MAYOR_QUE_SECUENCIA && !isMutant
                    && indexFila < dnaSequenceSize; i++) {
                indexFila = i + 1;
                sequence.append(dnaSequence[indexFila][indexColumna - 1]);
                indexColumna -= 1;
            }
            isMutant = findSequence(sequence.toString());
            sequenceLength = sequence.length();
            sequence = new StringBuilder("");
            valorInicialIndex += 1;
            indexColumna = 6;
            dnaSequenceSize = indexFila;
            indexFila = 0;
        }
        return isMutant;
    }
	
	
	/**
	 * Busca la secuencia en la cadena de ADN ingresada
	 * @param sequence Cadena de ADN a validar
	 * @return true si encuentra cadena ADN Mutante, 
	 * false en caso contrario
	 */
	private static boolean findSequence(String sequence){
        Predicate<String> p1 = (String strDNA) -> strDNA.contains(CONST_CCCC);
        Predicate<String> p2 = (String strDNA) -> strDNA.contains(CONST_TTTT);
        Predicate<String> p3 = (String strDNA) -> strDNA.contains(CONST_AAAA);
        Predicate<String> p4 = (String strDNA) -> strDNA.contains(CONST_GGGG);

        Predicate<String> pGeneral = p1.or(p2).or(p3).or(p4);

        if(contSecuencias > 1){
            return true;
        }else if(pGeneral.test(sequence) ){
            contSecuencias++;
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
        if(findDiagonalPrincipalLeft(dnaSequence))
            return true;
        if(findDiagonalPrincipalRight(dnaSequence))
            return true;
        if(findDiagonalsLeftInitial(dnaSequence))
            return true;
        if(findDiagonalsRightInitial(dnaSequence))
            return true;
        if(findDiagonalsLeftFinal(dnaSequence))
            return true;
        return findDiagonalsRightFinal(dnaSequence);
    }
	
	/**
	 * Realiza validaciones previas antes de empezar la búsqueda del adn,
	 * las validaciones que realiza es de longitud, caractéres aceptados
	 * @param dna Arreglo con todas las secuencias de ADN
	 * @return Arreglo con las secuencias de ADN
	 * @throws MutantException Excepción de tipo MutantException
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
