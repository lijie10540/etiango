package com.yitian.base.utils;

public class ByteArrayUtils {

	public static int compare(byte[] source, byte[] dest, int length) {
		for (int i = 0; i < length; i++) {
			if (source[i] != dest[i]) {
				return dest[i] - source[i];
			}
		}
		return 0;
	}


	/**
	 *  杩?
	 *
	 *@param  source  Description of Parameter
	 *@param  target  Description of Parameter
	 *@return         Description of the Returned Value
	 */
	public static int indexOf(byte[] source, byte[] target) {
		return indexOf(source, 0, source.length, target, 0, target.length, 0);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  source     Description of Parameter
	 *@param  target     Description of Parameter
	 *@param  fromIndex  Description of Parameter
	 *@return            Description of the Returned Value
	 */
	public static int indexOf(byte[] source, byte[] target, int fromIndex) {
		return indexOf(source, 0, source.length, target, 0, target.length, fromIndex);
	}


	/**
	 *  target 
	 *
	 *@param  source        Description of Parameter
	 *@param  sourceOffset  Description of Parameter
	 *@param  sourceCount   Description of Parameter
	 *@param  target        Description of Parameter
	 *@param  targetOffset  Description of Parameter
	 *@param  targetCount   Description of Parameter
	 *@param  fromIndex     Description of Parameter
	 *@return               Description of the Returned Value
	 */
	static int indexOf(byte[] source, int sourceOffset, int sourceCount,
			byte[] target, int targetOffset, int targetCount,
			int fromIndex) {
		if (fromIndex >= sourceCount) {
			return (targetCount == 0 ? sourceCount : -1);
		}
		if (fromIndex < 0) {
			fromIndex = 0;
		}
		if (targetCount == 0) {
			return fromIndex;
		}

		byte first = target[targetOffset];
		int i = sourceOffset + fromIndex;
		int max = sourceOffset + (sourceCount - targetCount);

		startSearchForFirstChar:
		while (true) {
			/*
			 *  Look for first character.
			 */
			while (i <= max && source[i] != first) {
				i++;
			}
			if (i > max) {
				return -1;
			}

			/*
			 *  Found first character, now look at the rest of v2
			 */
			int j = i + 1;
			int end = j + targetCount - 1;
			int k = targetOffset + 1;
			while (j < end) {
				if (source[j++] != target[k++]) {
					i++;
					/*
					 *  Look for str's first char again.
					 */
					continue startSearchForFirstChar;
				}
			}
			return i - sourceOffset;
			/*
			 *  Found whole string.
			 */
		}
	}
  static public int checkSum(byte [] data){
    int packageDataSum = 0;
    for (int i = 0; i < data.length; i++) {
      packageDataSum += (data[i] & 0xff);
    }
   return packageDataSum;
  }

}
