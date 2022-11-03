import { createGlobalStyle } from 'styled-components';

const GlobalStyle = createGlobalStyle`
  /* stack.css */
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

  /* primary.css */
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
  html,
  html body {
    min-width: auto;
  }
  body {
    padding-top: 50px;
  }
  @media print {
    body {
      padding-top: 0px;
    }
  }
  select,
  input,
  button {
    font-size: 100%;
  }
  h1,
  h2,
  h3 {
    line-height: 1.3;
    margin: 0 0 1em;
  }
  ul,
  ol,
  li {
    margin: 0;
    padding: 0;
  }
  ul,
  ol {
    margin-left: 30px;
    margin-bottom: 1em;
  }
  ul ul,
  ol ul,
  ul ol,
  ol ol {
    margin-bottom: 0;
  }
  ul {
    list-style-type: disc;
  }
  ol {
    list-style-type: decimal;
  }
  a {
    color: #0074CC;
    text-decoration: none;
    cursor: pointer;
    user-select: auto;
  }
  a:hover, a:active {
    color: #0A95FF;
    text-decoration: none;
  }
  p {
    clear: both;
    margin-bottom: 1em;
    margin-top: 0;
  }
`;

export default GlobalStyle;
