public class Core {
    /**
     * Get password hash.
     * Password is hashed with SHA-256.
     * Password salt
     * @param password
     * @return
     */
    public static String hashPassword(String password) {
        String salt = "101!th@t$fUnny"; // password salt
        String hash = ""; // password hash
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            hash = sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash;
    }
}
