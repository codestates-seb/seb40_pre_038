import { createGlobalStyle } from 'styled-components';

const GlobalStyle = createGlobalStyle`
  body {
    min-width: auto;
  }
  html,
  body,
  div,
  span,
  applet,
  object,
  iframe,
  h1,
  h2,
  h3,
  h4,
  h5,
  h6,
  p,
  blockquote,
  pre,
  a,
  abbr,
  acronym,
  address,
  big,
  cite,
  code,
  del,
  dfn,
  em,
  img,
  ins,
  kbd,
  q,
  s,
  samp,
  small,
  strike,
  strong,
  sub,
  sup,
  tt,
  var,
  b,
  u,
  i,
  center,
  dl,
  dt,
  dd,
  ol,
  ul,
  li,
  fieldset,
  form,
  label,
  legend,
  table,
  caption,
  tbody,
  tfoot,
  thead,
  tr,
  th,
  td,
  article,
  aside,
  canvas,
  details,
  embed,
  figure,
  figcaption,
  footer,
  header,
  hgroup,
  menu,
  nav,
  output,
  ruby,
  section,
  summary,
  time,
  mark,
  audio,
  video {
    margin: 0;
    padding: 0;
    border: 0;
    font: inherit;
    font-size: 100%;
    vertical-align: baseline;
  }
  article,
  aside,
  details,
  figcaption,
  figure,
  footer,
  header,
  hgroup,
  menu,
  nav,
  section {
    display: block;
  }
  body {
    line-height: 1;
  }
  ol,
  ul {
    list-style: none;
  }
  blockquote,
  q {
    quotes: none;
  }
  blockquote:before,
  blockquote:after,
  q:before,
  q:after {
    content: '';
    content: none;
  }
  table {
    border-collapse: collapse;
    border-spacing: 0;
  }
  sub,
  sup {
    font-size: 80%;
    line-height: 1;
    vertical-align: sub;
  }
  sup {
    vertical-align: super;
  }
  button {
    margin: 0;
  }
  h1,
  h2,
  h3 {
    line-height: 1.3;
    margin: 0 0 1em;
  }

  html,
  body {
    color: #232629;
    font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI Adjusted',
      'Segoe UI', 'Liberation Sans', sans-serif;
    font-size: 13px;
    line-height: calc((13 + 4) / 13);
  }
  @media (max-width: 640px) {
    html,
    html body {
      font-size: 11px;
    }
  }
  @media (max-width: 640px) {
    html,
    html body {
      font-size: 11px;
    }
  }
  body {
    box-sizing: border-box;
    min-height: 100%;
    background-color: #ffffff;
  }
  body *,
  body *:before,
  body *:after {
    box-sizing: inherit;
  }
`;

export default GlobalStyle;
