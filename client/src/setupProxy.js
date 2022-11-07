const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function (app) {
  app.use(
    [
      '/api/questions',
      '/api/answers',
      '/api/search',
      '/api/tags',
      '/api/users',
      '/login',
      '/api/comments',
    ],
    createProxyMiddleware({
      target:
        'http://ec2-43-201-113-224.ap-northeast-2.compute.amazonaws.com:8080',
      changeOrigin: true,
    })
  );
};
