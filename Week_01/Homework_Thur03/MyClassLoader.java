import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ClassName MyClassLoader
 * @Description 自定义ClassLoader
 * @Author yc
 * @Date 2020/10/21 16:21
 */
public class MyClassLoader extends ClassLoader{

	public static void main(String[] args) {
		MyClassLoader myClassLoader = new MyClassLoader();
		try {
			// 加载类
			Class<?> helloClass = myClassLoader.findClass("Hello");
			// 调用方法
			Method helloMethod = helloClass.getMethod("hello");
			Object helloInstance = helloClass.newInstance();
			helloMethod.invoke(helloInstance);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] bytes = new byte[0];
		String fileDir = this.getClass().getResource("/").getPath();
		System.out.println("currentDir = " + fileDir);
		File file = new File(fileDir + "Hello.xlass");
		try(FileInputStream is = new FileInputStream(file);
		    ByteArrayOutputStream out = new ByteArrayOutputStream()) {
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = is.read(buffer)) != -1) {
				for (int i = 0; i < bytesRead; i++) {
					buffer[i] = (byte) ~buffer[i];
				}
				out.write(buffer, 0, bytesRead);
			}
			bytes = out.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return defineClass(name, bytes, 0, bytes.length);
	}
}
