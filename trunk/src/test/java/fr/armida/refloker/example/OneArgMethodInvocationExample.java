package fr.armida.refloker.example;

import static fr.armida.refloker.Reflector.execute;
import static fr.armida.refloker.Reflector.executeAndReturnValue;
import static fr.armida.refloker.Reflector.on;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class OneArgMethodInvocationExample {
	public static class Example {
		public String argOfVoidMethod;

		public void voidMethod(String arg) {
			argOfVoidMethod = arg;
		}

		public String stringArgMethod(String arg) {
			return arg;
		}

		private String privateStringArgMethod(String arg) {
			return arg;
		}

		public Number numberArgMethod(Number arg) {
			return arg;
		}
	}

	private Example stubExample;

	@Before
	public void setUpExample() {
		stubExample = new Example();
	}

	@Test
	public void shouldInvokeVisibleVoidMethod() {
		execute(on(stubExample).invokeMethod("voidMethod").withArg("argument"));

		assertThat(stubExample.argOfVoidMethod, is("argument"));
	}

	@Test
	public void shouldInvokeVisibleStringArgMethod() {
		String value = (String) executeAndReturnValue(on(stubExample).invokeMethod("stringArgMethod").withArg("argument"));

		assertThat(value, is("argument"));
	}

	@Test
	public void shouldInvokeHiddenStrignArgMethod() {
		String value = (String) executeAndReturnValue(on(stubExample).invokeHiddenMethod("privateStringArgMethod").withArg("argument"));

		assertThat(value, is("argument"));
	}

	@Test
	public void shouldInvokeNumberArgMethod() {
		final Integer arg = 10;

		// type of formal parameter must be given when it's not the same as the
		// argument's class
		Number value = (Number) executeAndReturnValue(on(stubExample).invokeHiddenMethod("numberArgMethod").withArg(arg).ofType(Number.class));

		assertThat(value, is((Number) arg));
	}
}