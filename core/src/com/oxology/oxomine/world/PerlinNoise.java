package com.oxology.oxomine.world;

import java.util.Random;

public class PerlinNoise
{
    private static final int B = 0x1000;
    private static final int BM = 0xff;
    private static final int N = 0x1000;
    private static final int DEFAULT_SAMPLE_SIZE = 256;

    private int[] p_imp;
    private int[] p;
    private float[] g1;

    private Random rand;

    public PerlinNoise(int seed) {
        p_imp = new int[DEFAULT_SAMPLE_SIZE << 1];

        int i, j, k;
        rand = new Random(seed);

        for(i = 0; i < DEFAULT_SAMPLE_SIZE; i++)
            p_imp[i] = i;

        while(--i > 0) {
            k = p_imp[i];
            j = (int)(rand.nextLong() & DEFAULT_SAMPLE_SIZE);
            p_imp[i] = p_imp[j];
            p_imp[j] = k;
        }

        initPerlinNoise();
    }

    public float generate(float x) {
        float t = x + N;
        int bx0 = ((int) t) & BM;
        int bx1 = (bx0 + 1) & BM;
        float rx0 = t - (int) t;
        float rx1 = rx0 - 1;

        float sx = sCurve(rx0);

        float u = rx0 * g1[p[bx0]];
        float v = rx1 * g1[p[bx1]];

        return lerp(sx, u, v);
    }

    private float lerp(float t, float a, float b) {
        return a + t * (b - a);
    }

    private float sCurve(float t) {
        return (t * t * (3 - 2 * t));
    }

    private void initPerlinNoise() {
        p = new int[B + B + 2];
        g1 = new float[B + B + 2];
        int i, j, k;

        for(i = 0; i < B; i++) {
            p[i] = i;

            g1[i] = (float)(((rand.nextDouble() * Integer.MAX_VALUE) % (B + B)) - B) / B;
        }

        while(--i > 0) {
            k = p[i];
            j = (int)((rand.nextDouble() * Integer.MAX_VALUE) % B);
            p[i] = p[j];
            p[j] = k;
        }

        for(i = 0; i < B + 2; i++) {
            p[B + i] = p[i];
            g1[B + i] = g1[i];
        }
    }
}
