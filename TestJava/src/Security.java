import java.util.HashMap;

/**
 * 加解密算法
 * @author long
 */
public final class Security {

	private static HashMap<Byte,Byte> SEND_MAPPING = new HashMap<Byte,Byte>(256);
	
	private static HashMap<Byte,Byte> RECV_MAPPING = new HashMap<Byte,Byte>(256);
	
	private static final byte[] MAPPING = {
			-95,-62,-66,39,6,-120,-98,-7,15,-109,19,48,-13,-39,-89,80,
			-76,-41,-72,3,88,26,-25,11,-58,-8,118,30,-127,-83,70,-42,
			59,-51,35,28,18,-85,-106,34,-88,106,-87,85,58,97,-43,-50,
			-35,95,90,-94,125,-19,-93,-114,-128,122,54,1,66,-4,-44,-48,
			120,61,-80,56,102,31,-119,12,-121,-74,-56,110,-71,-110,84,23,
			-53,91,55,-102,-1,-111,-105,-125,-11,-54,7,25,-16,-99,107,-17,
			-90,29,82,94,113,-45,-64,40,-117,-69,-126,-101,-14,-78,16,-29,
			-57,-5,93,-23,60,124,-24,-21,-107,8,-77,68,-70,86,-122,114,
			37,116,123,47,109,117,77,-3,126,115,-113,-49,100,62,-92,27,
			-100,-33,-37,-59,-112,41,121,53,38,-104,52,-67,14,-103,20,21,
			-96,-73,-118,79,64,24,22,-97,0,103,-123,-15,-81,-32,-60,67,
			49,4,-2,111,57,42,112,74,-79,119,-26,-6,5,-61,-46,108,
			-12,45,69,17,43,-65,101,-55,51,44,-10,96,81,-20,105,-115,
			9,73,104,83,71,46,-52,-84,-31,-34,99,13,-40,36,-36,-47,
			32,78,-63,50,10,-22,-124,89,2,-30,65,-28,92,-27,-18,-68,
			76,127,-91,-9,-108,33,-116,-75,-86,-82,75,72,-38,98,63,87
	};
	static {
		int count = 0;
		for(byte i = Byte.MIN_VALUE;i<= Byte.MAX_VALUE;i++){
			SEND_MAPPING.put( i, MAPPING[count]);
			RECV_MAPPING.put(MAPPING[count], i);
			count++;
			if(i==Byte.MAX_VALUE){
				break;
			}
		}
	}
	
	/**
	 * 加密
	 * @param data
	 */
	public static void encrypt(byte[] data){
		encrypt(data, 0, data.length);
	}

	/**
	 * 加密
	 * @param data
	 * @param offset
	 * @param length
	 */
	public static void encrypt(byte[] data, int offset ,int length){
		if (data == null || data.length == 0 || data.length < (offset + length)) {
			return ;
		}
		for(int i = 0;i<length;i++){
			data[offset + i] = SEND_MAPPING.get(data[offset + i]);
		}
	}
	
	/**
	 * 解密
	 * @param data
	 */
	public static void decrypt(byte[] data){
		decrypt(data, 0, data.length);
	}
	
	/**
	 * 解密
	 * @param data
	 * @param offset
	 * @param length
	 */
	public static void decrypt(byte[] data, int offset ,int length){
		if (data == null || data.length == 0 || data.length < (offset + length)) {
			return ;
		}
		for(int i = 0;i<length;i++){
			data[offset + i] = RECV_MAPPING.get(data[offset +i]);
		}
	}
	//---------------------------------------------------
	public static void main(String[] args) {

//		byte[] original = {55,88,99};
		byte[] original = "一份朴素，一抹清新，就是生活最华美的底色。".getBytes();
		print(original);
		
		encrypt(original);
		print(original);
		
		decrypt(original);
		print(original);
		
		System.out.println("\r\n"+new String(original));
	}
	
	private static void print(byte[] data){
		System.out.print("\r\n");
		for(int i = 0;i<data.length;i++){
			if(i%16 == 0){
				System.out.println();
			}
			System.out.print(data[i]+",");
		}
		System.out.print("\r\n");
	}

}
