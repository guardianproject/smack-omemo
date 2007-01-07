/**
 * $RCSfile$
 * $Revision: $
 * $Date: $11-07-2006
 *
 * Copyright 2003-2006 Jive Software.
 *
 * All rights reserved. Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jivesoftware.smackx.jingle.media;

import org.jivesoftware.smackx.jingle.nat.TransportCandidate;

/**
 * Public Abstract Class provides a clear interface between Media Session and Jingle API.
 *
 * When a Jingle Session is fully stablished, we will have a Payload Type and two transport candidates defined for it.
 * Smack Jingle API don�t implement Media Transmit and Receive methods.
 * But provides an interface to let the user implements it using another API. For instance: JMF.
 *
 * <i>The Class that implements this one, must have the support to transmit and receive the jmf.</i>
 * <i>This interface let the user choose his own jmf API.</i>
 *
 * @author Thiago Camargo
 */
public abstract class JingleMediaSession {

    // Payload Type of the Session
    private PayloadType payloadType;
    // Local Transport details
    private TransportCandidate local;
    // Remote Transport details
    private TransportCandidate remote;

    /**
     * Creates a new JingleMediaSession Instance to handle Media methods.
     *
     * @param payloadType Payload Type of the transmittion
     * @param remote      Remote accepted Transport Candidate
     * @param local       Local accepted Transport Candidate
     */
    public JingleMediaSession(PayloadType payloadType, TransportCandidate remote,
                              TransportCandidate local) {
        this.local = local;
        this.remote = remote;
        this.payloadType = payloadType;
        initialize();
    }

    /**
     * Returns the PayloadType of the Media Session
     *
     * @return
     */
    public PayloadType getPayloadType() {
        return payloadType;
    }

    /**
     * Returns the Media Session local Candidate
     *
     * @return
     */
    public TransportCandidate getLocal() {
        return local;
    }

    /**
     * Returns the Media Session remote Candidate
     *
     * @return
     */
    public TransportCandidate getRemote() {
        return remote;
    }

    /**
     * Initialize the RTP Channel preparing to transmit and receive.
     */
    public abstract void initialize();

    /**
     * Starts a RTP / UDP / TCP Transmission to the remote Candidate
     */
    public abstract void startTrasmit();

    /**
     * Starts a RTP / UDP / TCP Receiver from the remote Candidate to local Candidate
     */
    public abstract void startReceive();

    /**
     * Set transmit activity. If the active is true, the instance should trasmit.
     * If it is set to false, the instance should pause transmit.
     * @param active
     */
    public abstract void setTrasmit(boolean active);

    /**
     * Stops a RTP / UDP / TCP Transmission to the remote Candidate
     */
    public abstract void stopTrasmit();

    /**
     * Stops a RTP / UDP / TCP Receiver from the remote Candidate to local Candidate
     */
    public abstract void stopReceive();

}