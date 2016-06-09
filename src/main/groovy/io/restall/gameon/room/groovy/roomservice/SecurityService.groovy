package io.restall.gameon.room.groovy.roomservice

import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import java.security.MessageDigest

class SecurityService {

  public String hmacSHA256(String content, String key) {
    Mac mac = Mac.getInstance("HmacSHA256")
    SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256")
    mac.init(secretKeySpec)
    byte[] digest = mac.doFinal(content.getBytes("UTF-8"))
    return digest.encodeBase64().toString()
  }

  public String hash(String data) {
    MessageDigest md = MessageDigest.getInstance("SHA-256")
    md.update(data.getBytes("UTF-8"))
    byte[] digest = md.digest()
    return Base64.getEncoder().encodeToString(digest)
  }

}
