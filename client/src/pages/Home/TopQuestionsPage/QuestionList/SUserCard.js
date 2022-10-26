const SUserCard = () => {
  return (
    <div className="s-user-card s-user-card__minimal">
      <div aria-live="polite">
        <div aria-live="polite">
          <a
            href="/users/16838860/ryan-avery"
            className="s-avatar s-avatar__16 s-user-card--avatar"
            data-user-id={16838860}
          >
            <div className="gravatar-wrapper-16">
              <img
                src="https://lh3.googleusercontent.com/a-/AOh14GiFVj1Ia9lDp-sMV9Fd7h4-cBNQrfQHhN_zuh-LaQ=k-s32"
                alt="Ryan Avery's user avatar"
                width={16}
                height={16}
                className="s-avatar--image"
              />
            </div>
          </a>
        </div>

        <div className="s-user-card--info">
          <div className="s-user-card--link d-flex gs4">
            <a href="/users/16838860/ryan-avery" className="flex--item">
              Ryan Avery
            </a>
          </div>
          <ul className="s-user-card--awards">
            <li className="s-user-card--rep">
              <span
                className="todo-no-class-here"
                title="reputation score "
                dir="ltr"
              >
                15
              </span>
            </li>
          </ul>
        </div>

        <time className="s-user-card--time">
          <a
            href="/questions/74189299/function-not-working-correctly-x-y-or-z"
            className="s-link s-link__muted"
          >
            asked
            <span title="2022-10-25 05:04:43Z" className="relativetime">
              24 secs ago
            </span>
          </a>
        </time>
      </div>
    </div>
  );
};

export default SUserCard;
