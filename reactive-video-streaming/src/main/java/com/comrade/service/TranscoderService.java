package com.comrade.service;

import org.bytedeco.javacv.*;
import org.bytedeco.ffmpeg.avcodec.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.ShortBuffer;

@Service
public class TranscoderService {


    public void split() {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("/home/shiva/Downloads/talking-people-6368.mp3");
            String channelOne = "/home/shiva/Downloads/1-1.mp3";
            String channelTwo = "/home/shiva/Downloads/2-1.mp3";
            ByteArrayOutputStream leftOutputStream = new ByteArrayOutputStream();
            ByteArrayOutputStream rightOutputStream = new ByteArrayOutputStream();
            //grabber from input
            FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(inputStream);
            System.out.println(grabber.getAudioChannels());
            grabber.setAudioChannels(2);
            grabber.setAudioCodec(avcodec.);
            grabber.start();
            //two recorders for two channels
            FFmpegFrameRecorder leftRecorder = new FFmpegFrameRecorder(channelOne, grabber.getImageWidth(), grabber.getImageHeight(), 1);
            leftRecorder.setSampleRate(grabber.getSampleRate());
            leftRecorder.setAudioChannels(1);
            leftRecorder.setFormat("mp3");
            leftRecorder.start();
            FFmpegFrameRecorder rightRecorder = new FFmpegFrameRecorder(channelTwo, grabber.getImageWidth(), grabber.getImageHeight(), 1);
            rightRecorder.setSampleRate(grabber.getSampleRate());
            rightRecorder.setFormat("mp3");
            rightRecorder.start();

            Frame frame = null;
            while ((frame = grabber.grabSamples()) != null) {
                // use s16le for example. so select ShortBuffer to receive the sample
                ShortBuffer sb = (ShortBuffer) frame.samples[0];
                short[] shorts = new short[sb.limit()];
                sb.get(shorts);
                //Split the LRLRLR to LLL in left channel and RRR int right channel
                Frame leftFrame = frame.clone();
                ShortBuffer leftSb = ShortBuffer.allocate(sb.capacity() / 2);
                leftFrame.samples = new Buffer[]{leftSb};
                leftFrame.audioChannels = 1;

                Frame rightFrame = frame.clone();
                ShortBuffer rightSb = ShortBuffer.allocate(sb.capacity() / 2);
                rightFrame.samples = new Buffer[]{rightSb};
                rightFrame.audioChannels = 1;

                for (int i = 0; i < shorts.length; i++) {
                    if (i % 2 == 0) {
                        leftSb.put(shorts[i]);
                    } else {
                        rightSb.put(shorts[i]);
                    }
                }
                // reset the buffer to read mode
                leftSb.rewind();
                rightSb.rewind();
                leftRecorder.record(leftFrame);
                rightRecorder.record(rightFrame);
            }

            //release source
            grabber.close();
            leftRecorder.close();
            rightRecorder.close();

        } catch (FileNotFoundException | FrameGrabber.Exception | FrameRecorder.Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void mergeAudio(String inputLeft, String inputRight, String output) {

        try {
            FFmpegFrameGrabber leftGrabber = new FFmpegFrameGrabber(inputLeft);
            leftGrabber.start();
            FFmpegFrameGrabber rightGrabber = new FFmpegFrameGrabber(inputRight);
            rightGrabber.start();
            FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(output, 2);
            //you'd better confirm the two input have the same samplerate. otherwise, you should control it manually by yourself
            recorder.setSampleRate(leftGrabber.getSampleRate());
            recorder.start();

            Frame leftFrame = null;
            Frame rightFrame = null;
            int index = 0;
            int maxLength = leftGrabber.getLengthInAudioFrames();
            while (index < maxLength) {
                // carry the bit data from two input into result frame by frame
                leftFrame = leftGrabber.grabSamples();
                rightFrame = rightGrabber.grabSamples();
                ShortBuffer leftSb = (ShortBuffer) leftFrame.samples[0];
                ShortBuffer rightSb = (ShortBuffer) rightFrame.samples[0];
                short[] leftShorts = new short[leftSb.limit()];
                short[] rightShorts = new short[rightSb.limit()];
                leftSb.get(leftShorts);
                rightSb.get(rightShorts);
                ShortBuffer mergeSb = ShortBuffer.allocate(leftSb.capacity() + rightSb.capacity());

                // create a template from the existing frame
                Frame mergeFrame = leftFrame.clone();
                // replace the frame tempalte by our merged buffer
                mergeFrame.samples = new Buffer[]{mergeSb};
                mergeFrame.audioChannels = 2;

                for (int i = 0; i < leftShorts.length; i++) {
                    mergeSb.put(leftShorts[i]);
                    mergeSb.put(rightShorts[i]);
                }

                //reset buffer to read mode
                mergeSb.flip();
                recorder.record(mergeFrame);
                index++;
            }
            //release source
            leftGrabber.close();
            rightGrabber.close();
            recorder.close();
        } catch (FrameGrabber.Exception | FrameRecorder.Exception e) {
            throw new RuntimeException(e);
        }

    }
}
