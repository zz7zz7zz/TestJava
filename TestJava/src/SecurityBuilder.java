import java.util.HashMap;
import java.util.Random;

/**
 * 加解密算法
 * @author long
 *
 */
public final class SecurityBuilder {
	
	private static final String SECRET = "浪淘沙·李煜\r帘外雨潺潺，春意阑珊，罗衾不耐五更寒。梦里不知身是客，一晌贪欢。\r独自莫凭栏，无限江山，别时容易见时难。流水落花春去也，天上人间。";
	
	private static final void generate(String key){
		
		Random mRandom = new Random();
		mRandom.setSeed(key.hashCode());
		
		int count = 0;
		byte[] flag = new byte[256];//标记
		HashMap<Byte,Byte> send_mapping = new HashMap<Byte,Byte>(256);
		HashMap<Byte,Byte> recv_mapping = new HashMap<Byte,Byte>(256);

		byte min = Byte.MIN_VALUE;
		byte max = Byte.MAX_VALUE;
		for(byte i = Byte.MIN_VALUE;i<= Byte.MAX_VALUE;i++){
			
			//1.找出一个没有被使用的数据
			byte value;
			while(true){
				value = (byte)(min+mRandom.nextInt(max - min + 1));
				if(flag[value + 128] == 1){
					continue;
				}else{
					flag[value + 128] = 1;
					break;
				}
			}
			
			//2.产生映射唤醒
			send_mapping.put(i, value);
			recv_mapping.put(value, i);
			
			count++;
			if(count >= 256){
				break;
			}
		}
		
		for(byte i = Byte.MIN_VALUE;i<= Byte.MAX_VALUE;i++){
			if(i%16 == 0){
				System.out.println();
			}
//			System.out.println("send "+ i +" = " + send_mapping.get(i));
			System.out.print(send_mapping.get(i) + ",");
			if(i==Byte.MAX_VALUE){
				break;
			}
		}
		
		System.out.println();
		
		for(byte i = Byte.MIN_VALUE;i<= Byte.MAX_VALUE;i++){
			if(i%16 == 0){
				System.out.println();
			}
//			System.out.println("send "+ i +" = " + recv_mapping.get(i));
			System.out.print(recv_mapping.get(i) + ",");
			if(i==Byte.MAX_VALUE){
				break;
			}
		}
	}

	//---------------------------------------------------
	public static void main(String[] args) {
		generate(SECRET);
	}

}
