package gwangjang.server.domain.auth.presentation;

//@RestController
//@AllArgsConstructor
//public class AuthRedirectController {
//    @Value("${android.package}")
//    private String androidPackage;
//
//    @Value("${android.scheme}")
//    private String androidScheme;
//
//    /**
//     * 애플 로그인 리다이렉트
//     */
//    @CrossOrigin(origins = "https://appleid.apple.com")
//    @PostMapping(value = "/auth/apple/callback", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//    public ResponseEntity<Object> userLoginAppleCallback(@RequestParam("code") String code,
//                                                         @RequestParam("id_token") String idToken) throws URISyntaxException {
//        // Deep link 생성
//        String callback = String.format("intent://callback?code=%s&id_token=%s#Intent;package=%s;scheme=%s;end",
//                code, idToken, androidPackage, androidScheme);
//
//        // 리다이렉트
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setLocation(new URI(callback));
//        return new ResponseEntity<>(httpHeaders, HttpStatus.TEMPORARY_REDIRECT);
//    }
//}
