package com.hcl.interviews;

import java.text.NumberFormat;
import java.util.Scanner;

public class NumberNamesImpl implements NumberNames {
	// Use this class to implement your exercise

	public static final String[] units = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
			"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen",
			"Nineteen" };

	public static final String[] tens = { "", // 0
			"", // 1
			"Twenty", // 2
			"Thirty", // 3
			"Fourty", // 4
			"Fifty", // 5
			"Sixty", // 6
			"Seventy", // 7
			"Eighty", // 8
			"Ninety" // 9
	};

	private static final String[] specialNames = { "", " Thousand", " Million", " Billion", " Trillion", " Quadrillion",
			" Quintillion" };

	@Override
	public String int2Text(long input) {
		String output="";
		if (input == 0) {
			return "zero";
		}

		String prefix = "";

		if (input < 0) {
			input = -input;
			prefix = "negative";
		}

		String current = "";
		int place = 0;

		do {
			long n = (input % 1000);
			if (n != 0) {
				String s = convertLessThanOneThousand(n);
				current = s + ""+ specialNames[place] +", "+ current;
			}
			place++;
			input /= 1000;
		} while (input > 0);
		output=(prefix + current).trim();
		int index = output.lastIndexOf(",");
		
		return output.substring(0, index).trim().toLowerCase();
	}

	private String convertLessThanOneThousand(long number) {
		String current;

		if (number % 100 < 20) {
			current = units[(int) (number % 100)];
			number /= 100;
		} else {
			current = units[(int) (number % 10)];
			number /= 10;

			current = tens[(int) (number % 10)] +"-"+current;
			number /= 10;
		}
		if (number == 0)
			return current;
		return units[(int) number] + " Hundred " + current;
	}

	public static void main(final String[] args) {

		long n;
		Scanner s = new Scanner(System.in);
		System.out.println("Enter a number to convert into word format");
		n = s.nextLong();
		NumberNamesImpl nm = new NumberNamesImpl();
		System.out.println(NumberFormat.getInstance().format(n) + "='" + nm.int2Text(n) + "'");

		System.out.println("*** " + nm.int2Text(1234567890123456L));
		System.out.println("*** " + nm.int2Text(0));

	}

}
